package com.vaishnavi.async.statement.download.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.vaishnavi.async.statement.download.dto.response.TransactionDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * This class generates the pdf
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */

@Service
@Slf4j
public class PDFGenerationService {

    public void createPDF(List<TransactionDetail> transactionDetails, String requestId) throws FileNotFoundException {
        log.info("Email service started");

        String pdfPath = requestId+".pdf";

            PdfWriter writer = new PdfWriter(pdfPath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
             Table table = new Table(4);

            table.addCell(new Cell().add("Transaction ID"));
            table.addCell(new Cell().add("Amount"));
            table.addCell(new Cell().add("Type"));
            table.addCell(new Cell().add("Description"));

            for (TransactionDetail transactionDetail : transactionDetails  ) {
                table.addCell(transactionDetail.getTransactionId().toString());
                table.addCell(transactionDetail.getAmount().toString());
                table.addCell(transactionDetail.getType());
                table.addCell(transactionDetail.getDescription());
            }
            document.add(table);
            document.close();
            log.info("PDF generated successfully for {} " ,requestId);

    }
}
