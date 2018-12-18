function myFunction() {
    let baseURL = 'http://localhost:8080/';
    let registrationNumber = document.getElementById("search_field").value;
    let completeURL = baseURL.concat(registrationNumber);

    $.getJSON(completeURL, function (data) {

        let studentRegistrationNumber = $("#search_field").val();
        let found = 0;

        $(".arrangement_record").remove();
        $(".table_div p").remove();
        for (let i = 0; i < data.length; i++) {
            if (data[i].studentRegistrationNumber === parseInt(studentRegistrationNumber, 10)) {
                found = 1;
                let arrangementRecord = "<tr class='arrangement_record'>"
                    + "<td>" + data[i].studentRegistrationNumber + "</td>"
                    + "<td>" + data[i].studentName + "</td>"
                    + "<td>" + data[i].batch + "</td>"
                    + "<td>" + data[i].courseCode + "</td>"
                    + "<td>" + data[i].courseName + "</td>"
                    + "<td>" + data[i].date + "</td>"
                    + "<td>" + data[i].day + "</td>"
                    + "<td>" + data[i].startTime + "</td>"
                    + "<td>" + data[i].endTime + "</td>"
                    + "<td>" + data[i].examLocation + "</td>"
                    + "</tr>";
                $("table tbody").append(arrangementRecord);
            }
        }

        let pdfURL = completeURL.concat('/pdf');

        let pdfGenerateLink = "<tr class='arrangement_record'>"
            + "<td colspan='10'>" + "<a href='#' id='getpdf'> Generate PDF </a>" + "</td>"
            + "</tr>";
        $("table tbody").append(pdfGenerateLink);


        $('#getpdf').click(function() {
            $.getJSON(pdfURL, function(pdf){
                $("#getpdf").text("PDF Generated In PDF Folder i.e. " + pdf.link);
                $("#getpdf").attr("href", "file:///home/dan/IdeaProjects/Examify/" + pdf.link);
            });
        });

        if (found === 0) {
            $(".table_div").append("<p class='error'>No Record Found For : " + studentRegistrationNumber + "</p>");
            $(".arrangement_record").remove();
        }

        if (found === 1) {
            $(".table_div").prepend("<p class='success'>Record Found For : " + studentRegistrationNumber + "</p>");
        }
    });
}