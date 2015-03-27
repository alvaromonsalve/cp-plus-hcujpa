package tools;

import com.itextpdf.text.pdf.PdfCopyFields;
import com.itextpdf.text.pdf.PdfReader;
import entidades_EJB.ConfigCups;
import entidades_EJB.InfoHistoriac;
import entidades_EJB.InfoInterconsultaHcu;
import entidades_EJB.InfoPosologiaHcu;
import entidades_EJB.InfoProcedimientoHcu;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import jpa.ConfigCupsJpaController;
import jpa.InfoHistoriacJpaController;
import jpa.InfoInterconsultaHcuJpaController;
import jpa.InfoPosologiaHcuJpaController;
import jpa.InfoProcedimientoHcuJpaController;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import oldConnection.Database;

/**
 *
 * @author Alvaro Monsalve
 */
public class ImpHcuReportes extends Thread {

    private InfoHistoriac idHcu;
    private InfoPosologiaHcuJpaController infoPosologiaHcuJPA = null;
    private EntityManagerFactory factory;
    private InfoProcedimientoHcuJpaController infoProcedimientoHcuJPA = null;
    private InfoInterconsultaHcuJpaController infoInterconsultaHcuJPA = null;
    private ConfigCupsJpaController ccjc = null;
    private final Properties props = new Properties();
    private final JLabel jLabel;
    private InfoHistoriacJpaController ihjc;
    private final Integer idHcuInt;

    public ImpHcuReportes(Integer idHcuInt, JLabel jLabel) {
        this.idHcuInt = idHcuInt;
        this.jLabel=jLabel;
    }

    @Override
    public void run() {
        try {
            jLabel.setVisible(true);
            props.put("javax.persistence.jdbc.user", "root");
            props.put("javax.persistence.jdbc.password", "9RLH5QEwQ4dF");
            props.put("javax.persistence.jdbc.url", "jdbc:mysql://192.168.1.102:3306/database");
            props.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            PdfReader reader1 = null, reader2 = null, reader3 = null, reader4 = null, reader5 = null, reader6 = null;
            File archivoTemporal = File.createTempFile("Historia_Urgencia", ".pdf");
            if (infoProcedimientoHcuJPA == null) {
                factory = Persistence.createEntityManagerFactory("ClipaEJBPU", props);
                infoProcedimientoHcuJPA = new InfoProcedimientoHcuJpaController(factory);
                ihjc = new InfoHistoriacJpaController(factory);
                ccjc = new ConfigCupsJpaController(factory);
            }
            List<InfoProcedimientoHcu> listInfoProcedimientoHcufilter = new ArrayList<InfoProcedimientoHcu>();
            idHcu = ihjc.findInfoHistoriac(idHcuInt);
            List<InfoProcedimientoHcu> listInfoProcedimientoHcu = infoProcedimientoHcuJPA.ListFindInfoProcedimientoHcu(idHcu);
            
            listInfoProcedimientoHcufilter.clear();
            for (InfoProcedimientoHcu iph : listInfoProcedimientoHcu) {
                ConfigCups cc = ccjc.findConfigCups(iph.getIdCups());
                if (cc.getIdEstructuraCups().getId() == 17) {
                    listInfoProcedimientoHcufilter.add(iph);
                }
            }
            if (listInfoProcedimientoHcufilter != null & listInfoProcedimientoHcufilter.size() > 0) {
                String master = null;
                master = System.getProperty("user.dir") + "/reportes/solicitudprocedimientolabposthcu.jasper";
                if (master != null) {
                    oldConnection.Database db = new Database(props);
                    db.Conectar();
                    Map param = new HashMap();
                    param.put("id_hc", idHcu.getId().toString());
                    param.put("NombreReport", "SOLICITUD DE PROCEDIMIENTOS DE LABORATORIO");
                    param.put("version", "1.0");
                    param.put("codigo", "R-FA-006");
                    param.put("servicio", "URGENCIAS");
                    param.put("novalido", "0");
                    JasperPrint informe = JasperFillManager.fillReport(master, param, db.conexion);
                    JRExporter exporter = new JRPdfExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, informe);
                    File tempFile = File.createTempFile("Solicitud_de_Procedimientos", ".pdf");
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, tempFile);
                    exporter.exportReport();
                    reader5 = new PdfReader(tempFile.getAbsolutePath());
                    db.DesconectarBasedeDatos();
                    tempFile.deleteOnExit();
                }
            }
            for (InfoProcedimientoHcu iph : listInfoProcedimientoHcu) {
                ConfigCups cc = ccjc.findConfigCups(iph.getIdCups());
                if (cc.getIdEstructuraCups().getId() != 17 && cc.getIdEstructuraCups().getId() != 15) {
                    listInfoProcedimientoHcufilter.add(iph);
                }
            }
            if (listInfoProcedimientoHcufilter != null & listInfoProcedimientoHcufilter.size() > 0) {
                String master = null;
                master = System.getProperty("user.dir") + "/reportes/solicitudprocedimientos.jasper";
                if (master != null) {
                    oldConnection.Database db = new Database(props);
                    db.Conectar();
                    Map param = new HashMap();
                    param.put("id_hc", idHcu.getId());
                    param.put("NombreReport", "SOLICITUD DE PROCEDIMIENTOS");
                    param.put("version", "1.0");
                    param.put("codigo", "R-FA-005");
                    param.put("servicio", "URGENCIAS");
                    param.put("novalido", "0");
                    JasperPrint informe = JasperFillManager.fillReport(master, param, db.conexion);
                    JRExporter exporter = new JRPdfExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, informe);
                    File tempFile = File.createTempFile("Solicitud_de_Procedimientos", ".pdf");
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, tempFile);
                    exporter.exportReport();
                    reader1 = new PdfReader(tempFile.getAbsolutePath());
                    db.DesconectarBasedeDatos();
                    tempFile.deleteOnExit();
                }
            }

            listInfoProcedimientoHcufilter.clear();//15
            for (InfoProcedimientoHcu iph : listInfoProcedimientoHcu) {
                ConfigCups cc = ccjc.findConfigCups(iph.getIdCups());
                if (cc.getIdEstructuraCups().getId() == 15) {
                    listInfoProcedimientoHcufilter.add(iph);
                }
            }
            if (listInfoProcedimientoHcufilter != null & listInfoProcedimientoHcufilter.size() > 0) {
                String master = null;
                master = System.getProperty("user.dir") + "/reportes/solicitudprocedimientorxposthcu.jasper";
                if (master != null) {
                    oldConnection.Database db = new Database(props);
                    db.Conectar();
                    Map param = new HashMap();
                    param.put("id_hc", idHcu.getId().toString());
                    param.put("NombreReport", "SOLICITUD DE PROCEDIMIENTOS DE IMAGENOLOGIA");
                    param.put("version", "1.0");
                    param.put("codigo", "R-FA-007");
                    param.put("servicio", "URGENCIAS");
                    param.put("novalido", "0");
                    JasperPrint informe = JasperFillManager.fillReport(master, param, db.conexion);
                    JRExporter exporter = new JRPdfExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, informe);
                    File tempFile = File.createTempFile("Solicitud_de_Procedimientos", ".pdf");
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, tempFile);
                    exporter.exportReport();
                    reader6 = new PdfReader(tempFile.getAbsolutePath());
                    db.DesconectarBasedeDatos();
                    tempFile.deleteOnExit();
                }
            }
            if (infoPosologiaHcuJPA == null) {
                infoPosologiaHcuJPA = new InfoPosologiaHcuJpaController(factory);
            }
            List<InfoPosologiaHcu> listInfoPosologiaHcu = infoPosologiaHcuJPA.ListFindInfoPosologia(idHcu);
            if (listInfoPosologiaHcu.size() > 0) {
                String master = null;
                Map param = new HashMap();
                master = System.getProperty("user.dir") + "/reportes/solicitudmedicamentos.jasper";
                if (master != null) {
                    oldConnection.Database db = new Database(props);
                    db.Conectar();
                    param.put("id_hc", idHcu);
                    param.put("NombreReport", "PRESCRIPCION MEDICA");
                    param.put("version", "1.0");
                    param.put("codigo", "R-FA-003");
                    param.put("servicio", "URGENCIAS");
                    JasperPrint informe = JasperFillManager.fillReport(master, param, db.conexion);
                    JRExporter exporter = new JRPdfExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, informe);
                    File tempFile = File.createTempFile("Prescripcion_Medica", ".pdf");
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, tempFile);
                    exporter.exportReport();
                    reader2 = new PdfReader(tempFile.getAbsolutePath());
                    db.DesconectarBasedeDatos();
                    tempFile.deleteOnExit();
                }
            }
            if (infoInterconsultaHcuJPA == null) {
                infoInterconsultaHcuJPA = new InfoInterconsultaHcuJpaController(factory);
            }
            List<InfoInterconsultaHcu> listInfoInterconsultaHcu = infoInterconsultaHcuJPA.listInterconsultaHcu(idHcu);
            if (listInfoInterconsultaHcu.size() > 0) {
                String master = System.getProperty("user.dir") + "/reportes/solValoracion.jasper";
                if (master != null) {
                    oldConnection.Database db = new Database(props);
                    db.Conectar();
                    Map param = new HashMap();
                    param.put("id_hc", idHcu.getId());
                    param.put("entidadadmision", idHcu.getIdInfoAdmision().getIdEntidadAdmision().getNombreEntidad());
                    param.put("NombreReport", "SOLICITUD DE VALORACION POR ESPECIALISTA");
                    param.put("version", "1.0");
                    param.put("codigo", "R-FA-004");
                    param.put("servicio", "URGENCIAS");
                    JasperPrint informe = JasperFillManager.fillReport(master, param, db.conexion);
                    JRExporter exporter = new JRPdfExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, informe);
                    File tempFile = File.createTempFile("Solicitud_de_Valoracion", ".pdf");
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, tempFile);
                    exporter.exportReport();
                    reader3 = new PdfReader(tempFile.getAbsolutePath());
                    db.DesconectarBasedeDatos();
                    tempFile.deleteOnExit();
                }
            }
            String master = System.getProperty("user.dir") + "/reportes/HClinica.jasper";
            if (master != null) {
                oldConnection.Database db = new Database(props);
                db.Conectar();
                Map param = new HashMap();
                param.put("idHC", idHcu.getId());
                param.put("NameReport", "HISTORIA CLINICA DE INGRESO");
                param.put("version", "1.0");
                param.put("codigo", "R-FA-002");
                param.put("servicio", "URGENCIAS");
                JasperPrint informe = JasperFillManager.fillReport(master, param, db.conexion);
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, informe);
                File tempFile = File.createTempFile("historia_urgencia", ".pdf");
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, tempFile);
                exporter.exportReport();
                reader4 = new PdfReader(tempFile.getAbsolutePath());
                db.DesconectarBasedeDatos();
                tempFile.deleteOnExit();
            }
            PdfCopyFields copy = new PdfCopyFields(new FileOutputStream(archivoTemporal));
            if (reader1 != null) {
                copy.addDocument(reader1);
            }
            if (reader2 != null) {
                copy.addDocument(reader2);
            }
            if (reader5 != null) {
                copy.addDocument(reader5);
            }
            if (reader6 != null) {
                copy.addDocument(reader6);
            }
            if (reader3 != null) {
                copy.addDocument(reader3);
            }
            if (reader4 != null) {
                copy.addDocument(reader4);
            }
            try {
                copy.close();
                jLabel.setVisible(false);
                Desktop.getDesktop().open(archivoTemporal);  
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "El documento no contiene paginas", "Clipa+", JOptionPane.INFORMATION_MESSAGE);
            }
            archivoTemporal.deleteOnExit();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "10076:\n" + ex.getMessage(), ImpHcuReportes.class.getName(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
