/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliCorreodestino;
import cr.ac.una.wsclinicauna.model.CliCorreodestinoDto;
import cr.ac.una.wsclinicauna.model.CliParametroconsulta;
import cr.ac.una.wsclinicauna.model.CliParametroconsultaDto;
import cr.ac.una.wsclinicauna.model.CliReporte;
import cr.ac.una.wsclinicauna.model.CliReporteDto;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author ArauzKJ
 */
@Stateless
@LocalBean
public class CliReporteService {

    private static final Logger LOG = Logger.getLogger(CliReporteService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta generarInformeExcelDesdeConsultaSQL(CliReporteDto cliReporteDto) {
        try {
//            String consulta = "SELECT u.usu_nombre, u.usu_papellido, u.usu_cedula FROM CLI_USUARIO u WHERE u.usu_nombre LIKE {nombre}";
            String consulta = cliReporteDto.getRepConsulta(); // Conulta SQL

            for (CliParametroconsultaDto para : cliReporteDto.getCliParametroconsultaList()) { // Remplazo parametros
                String parametro = para.getParcParametro();
                String valor = para.getParcValor();

                Pattern pattern = Pattern.compile("\\{" + parametro + "\\}");
                Matcher matcher = pattern.matcher(consulta);

                consulta = matcher.replaceAll(Matcher.quoteReplacement(valor));
            }

            Connection co = em.unwrap(Connection.class); // Coneccion

            Statement statement = co.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta); // Ejecuta la consulta

            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Informe");

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColumns = metaData.getColumnCount();

            String titulo = cliReporteDto.getRepTitulo(); // Titulo informe
            Font titleFont = workbook.createFont();
            titleFont.setFontHeightInPoints((short) 14);
            titleFont.setBold(true);
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setFont(titleFont);
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue(titulo);
            titleCell.setCellStyle(titleCellStyle);

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, numColumns - 1));

            int numParametros = cliReporteDto.getCliParametroconsultaList().size();

            Row parameterRow = sheet.createRow(2);

            Cell parameterHeaderCell = parameterRow.createCell(0);
            parameterHeaderCell.setCellValue("Parámetros"); // Titulo parametros

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));

            for (int i = 0; i < numParametros; i++) { // Parametros
                CliParametroconsultaDto parametro = cliReporteDto.getCliParametroconsultaList().get(i);
                Row currentRow = sheet.createRow(i + 3);
                Cell paramNameCell = currentRow.createCell(0);
                paramNameCell.setCellValue(parametro.getParcParametro());

                Cell paramValueCell = currentRow.createCell(1);
                paramValueCell.setCellValue(parametro.getParcValor());

                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
            }

            Row headerRow = sheet.createRow(4 + numParametros); // Titulos Valores
            for (int i = 1; i <= numColumns; i++) {
                String columnName = metaData.getColumnName(i);
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(columnName);
                sheet.autoSizeColumn(i - 1);
            }

            int rowNum = 5 + numParametros;
            while (resultSet.next()) { // Valores
                Row dataRow = sheet.createRow(rowNum);
                for (int i = 1; i <= numColumns; i++) {
                    Cell cell = dataRow.createCell(i - 1);
                    cell.setCellValue(resultSet.getString(i));
                    sheet.autoSizeColumn(i - 1);
                }
                rowNum++;
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ReporteExcel", outputStream.toByteArray());
        } catch (Exception e) {
            Logger.getLogger(CliReporteService.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public Respuesta getReporte(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliReporte.findByRepId", CliReporte.class);
            qryUsuario.setParameter("repId", id);
            CliReporte cliReporte = (CliReporte) qryUsuario.getSingleResult();

            CliReporteDto cliReporteDto = new CliReporteDto(cliReporte);

            for (CliCorreodestino cliCorreodestino : cliReporte.getCliCorreodestinoList()) {
                cliReporteDto.getCliCorreodestinoList().add(new CliCorreodestinoDto(cliCorreodestino));
            }
            for (CliParametroconsulta cliParametroconsulta : cliReporte.getCliParametroconsultaList()) {
                cliReporteDto.getCliParametroconsultaList().add(new CliParametroconsultaDto(cliParametroconsulta));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporte", cliReporteDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un Reporte con el código ingresado.", "getReporte NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Reporte.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Reporte.", "getReporte NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Reporte.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Reporte.", "getReporte " + ex.getMessage());
        }
    }

    public Respuesta getReportes() {
        try {
            Query qryUsuario = em.createNamedQuery("CliReporte.findAll", CliReporte.class);
            List<CliReporte> cliReportes = qryUsuario.getResultList();
            List<CliReporteDto> cliReporteDtos = new ArrayList<>();
            for (CliReporte cliReporte : cliReportes) {
                CliReporteDto cliReporteDto = new CliReporteDto(cliReporte);

                for (CliParametroconsulta cliParametroconsulta : cliReporte.getCliParametroconsultaList()) {
                    cliReporteDto.getCliParametroconsultaList().add(new CliParametroconsultaDto(cliParametroconsulta));
                }
                for (CliCorreodestino cliCorreodestino : cliReporte.getCliCorreodestinoList()) {
                    cliReporteDto.getCliCorreodestinoList().add(new CliCorreodestinoDto(cliCorreodestino));
                }

                cliReporteDtos.add(cliReporteDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reportes", cliReporteDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen Reporte con los criterios ingresados.", "getReportes NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Reporte.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Reporte.", "getReportes " + ex.getMessage());
        }
    }

    public Respuesta guardarReporte(CliReporteDto cliReporteDto) {
        try {
            CliReporte cliReporte;
            if (cliReporteDto.getRepId() != null && cliReporteDto.getRepId() > 0) {
                cliReporte = em.find(CliReporte.class, cliReporteDto.getRepId());
                if (cliReporte == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Reporte a modificar.", "guardarReporte NoResultException");
                }
                cliReporte.actualizar(cliReporteDto);

                for (CliCorreodestinoDto cliCorreodestinoDto : cliReporteDto.getCliCorreodestinoList()) {
                    if (cliCorreodestinoDto.getModificado()) {
                        CliCorreodestino cliCorreodestino = em.find(CliCorreodestino.class, cliCorreodestinoDto.getCdId());
                        cliCorreodestino.setRepId(cliReporte);
                        cliReporte.getCliCorreodestinoList().add(cliCorreodestino);
                    }
                }

                for (CliCorreodestinoDto cliCorreodestinoDto : cliReporteDto.getCliCorreodestinoListEliminados()) {
                    CliCorreodestino cliCorreodestino = em.find(CliCorreodestino.class, cliCorreodestinoDto.getCdId());
                    cliReporte.getCliCorreodestinoList().remove(new CliCorreodestino(cliCorreodestinoDto.getCdId()));
                    em.remove(cliCorreodestino);
                }

                for (CliParametroconsultaDto cliParametroconsultaDto : cliReporteDto.getCliParametroconsultaList()) {
                    if (cliParametroconsultaDto.getModificado()) {
                        CliParametroconsulta cliParametroconsulta = em.find(CliParametroconsulta.class, cliParametroconsultaDto.getParcId());
                        cliParametroconsulta.setRepId(cliReporte);
                        cliReporte.getCliParametroconsultaList().add(cliParametroconsulta);
                    }
                }

                for (CliParametroconsultaDto cliParametroconsultaDto : cliReporteDto.getCliParametroconsultaListEliminados()) {
                    CliParametroconsulta cliParametroconsulta = em.find(CliParametroconsulta.class, cliParametroconsultaDto.getParcId());
                    cliReporte.getCliParametroconsultaList().remove(new CliParametroconsulta(cliParametroconsultaDto.getParcId()));
                    em.remove(cliParametroconsulta);
                }

                cliReporte = em.merge(cliReporte);
            } else {
                cliReporte = new CliReporte(cliReporteDto);
                em.persist(cliReporte);
            }
            em.flush();
            CliReporteDto reporteDto = new CliReporteDto(cliReporte);
            for (CliParametroconsulta cliParametroconsulta : cliReporte.getCliParametroconsultaList()) {
                reporteDto.getCliParametroconsultaList().add(new CliParametroconsultaDto(cliParametroconsulta));
            }
            for (CliCorreodestino cliCorreodestino : cliReporte.getCliCorreodestinoList()) {
                reporteDto.getCliCorreodestinoList().add(new CliCorreodestinoDto(cliCorreodestino));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporte", reporteDto);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Reporte.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Reporte.", "guardarReporte " + ex.getMessage());
        }
    }

    public Respuesta eliminarReporte(Long id) {
        try {
            CliReporte cliReporte;
            if (id != null && id > 0) {
                cliReporte = em.find(CliReporte.class, id);
                if (cliReporte == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Reporte a eliminar.", "eliminarReporte NoResultException");
                }
                for (CliParametroconsulta cliParametroconsulta : cliReporte.getCliParametroconsultaList()) {
                    em.remove(cliParametroconsulta);
                }
                for (CliCorreodestino cliCorreodestino : cliReporte.getCliCorreodestinoList()) {
                    em.remove(cliCorreodestino);
                }
                em.remove(cliReporte);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el Reporte a eliminar.", "eliminarReporte NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el Reporte porque tiene relaciones con otros registros.", "eliminarExpediente " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Reporte.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Reporte.", "eliminarReporte " + ex.getMessage());
        }
    }
}
