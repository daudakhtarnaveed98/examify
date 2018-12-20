package com.creativosoft.examify.helpers;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Row;
import com.creativosoft.examify.models.ArrangementRecord;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;
import java.util.List;

public abstract class PDF {
    public static void createPDF(List<ArrangementRecord> arrangements, int studentRegistrationNumber) throws IOException {
        // Setting up document and page.
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));

        // Creating and beginning content stream.
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();

        // Setting up fonts, leading, margins and positions.
        float margin = 25;
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float bottomMargin = 50;
        float yPosition = 50;

        // Creating base table.
        BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, true);

        // Creating and adding rows and columns.
        Row<PDPage> headerRow = table.createRow(15f);
        headerRow.createCell(10, "Student Registration No.");
        headerRow.createCell(15, "Student Name");
        headerRow.createCell(15, "Course Name");
        headerRow.createCell(10, "Exam Date");
        headerRow.createCell(10, "Exam Day");
        headerRow.createCell(10, "Exam Start Time");
        headerRow.createCell(10, "Exam End Time");
        headerRow.createCell(20, "Exam Location");
        table.addHeaderRow(headerRow);

        for (ArrangementRecord arrangementRecord : arrangements) {
            // Formatting date.
            String date = arrangementRecord.getDate().toString();
            date = date.replace("00:00:00.0", "");

            Row<PDPage> row = table.createRow(12f);
            row.createCell(10, Integer.toString(arrangementRecord.getStudentRegistrationNumber()));
            row.createCell(15, arrangementRecord.getStudentName());
            row.createCell(15, arrangementRecord.getCourseName());
            row.createCell(10, date);
            row.createCell(10, arrangementRecord.getDay());
            row.createCell(10, arrangementRecord.getStartTime().toString());
            row.createCell(10, arrangementRecord.getEndTime().toString());
            row.createCell(20, arrangementRecord.getExamLocation());
        }

        // Drawing table.
        table.draw();

        // Ending content streams and adding page.
        contentStream.endText();
        contentStream.close();

        // Saving file.
        String savePath = "pdf/" + studentRegistrationNumber + "_arrangements" + ".pdf";
        document.save(savePath);

        // Closing document.
        document.close();
    }
}