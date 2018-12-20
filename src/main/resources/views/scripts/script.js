// Variables.
let baseURL = "http://localhost:8080/";


/*
*
* Function  :   getArrangementRecords, it is entry point into this script from frontend.
* Parameters:   none.
* Returns   :   nothing.
*
*/
function getArrangementRecords() {
    // Variables for url formation and returning json objects.
    let studentRegistrationNumber = document.getElementById("search_field").value;
    let completeURL;

    // If studentRegistrationNumber is not null then forming url and proceeding.
    if (studentRegistrationNumber !== "") {
        completeURL = baseURL.concat(studentRegistrationNumber);

        // Getting JSON objects of arrangement records from completeURL.
        $.getJSON(completeURL, function(recordsJSON) {
            populateTable(recordsJSON, studentRegistrationNumber);
        });
    }
}


/*
*
* Function  :   populateTable, it populates the table based on the JSON of arrangement records.
* Parameters:   recordsJSON -> json objects of arrangements.
* Returns   :   nothing.
*
*/
function populateTable(recordsJSON, studentRegistrationNumber) {
    // Variable to hold status of records.
    let found = false;
    let tableRows = $(".arrangement_record").remove();
    let tableDiv = $(".table_div");

    $(".table_div p").remove();
    tableRows.remove();

    for (let i = 0; i < recordsJSON.length; i++) {
        if (recordsJSON[i].studentRegistrationNumber === parseInt(studentRegistrationNumber, 10)) {
            found = true;
            let arrangementRecord = "<tr class='arrangement_record'>"
                + "<td>" + recordsJSON[i].studentRegistrationNumber + "</td>"
                + "<td>" + recordsJSON[i].studentName               + "</td>"
                + "<td>" + recordsJSON[i].batch                     + "</td>"
                + "<td>" + recordsJSON[i].courseCode                + "</td>"
                + "<td>" + recordsJSON[i].courseName                + "</td>"
                + "<td>" + recordsJSON[i].date                      + "</td>"
                + "<td>" + recordsJSON[i].day                       + "</td>"
                + "<td>" + recordsJSON[i].startTime                 + "</td>"
                + "<td>" + recordsJSON[i].endTime                   + "</td>"
                + "<td>" + recordsJSON[i].examLocation              + "</td>"
                + "</tr>";
            $("table tbody").append(arrangementRecord);
        }
    }

    if (found === false) {
        tableDiv.append("<p class='error'>No Record Found For : " + studentRegistrationNumber + "</p>");
        tableRows.remove();
    }

    if (found === true) {
        tableDiv.prepend("<p class='success'>Record Found For : " + studentRegistrationNumber + "</p>");
        tableDiv.append("<p class='generate_pdf'><a href='#' onclick='generatePDF()'>Generate PDF</a></p>");
    }
}


/*
*
* Function  :   generatePDF, it generates PDF for a given user.
* Parameters:   none.
* Returns   :   nothing.
*
*/
function generatePDF() {
    // Variables for url formation and returning json objects.
    let studentRegistrationNumber = document.getElementById("search_field").value;
    let pdfGenerationURL = baseURL.concat(studentRegistrationNumber).concat("/pdf");

    // Generating PDF in pdf folder.
    $.getJSON(pdfGenerationURL, function(pdfLink) {
        // Displaying response on frontend.
        $(".generate_pdf").text("PDF Generated @: <ProjectRoot>/" + pdfLink.link);
    });
}
