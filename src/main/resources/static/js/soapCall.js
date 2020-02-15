function invokeSoap() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open('POST', 'http://localhost:8080/ws', true);
    var xml = document.getElementById("xmlText").value;
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == 200) {
                createResultHeader();

                console.log(xmlhttp.responseText)
                //alert(xmlhttp.responseText);
                //document.getElementById('xmlResult').innerHTML = xmlhttp.responseText;
                var xmlDoc = $.parseXML(xmlhttp.responseText);

                var jqObj = $(xmlDoc);
                jqObj.find('ns2\\:book').each(function(index){
                    let title = $(this).find('ns2\\:title').text();
                    let author = $(this).find('ns2\\:author').text();
                    let description = $(this).find('ns2\\:description').text();
                    let rating = $(this).find('ns2\\:rating').text();

                    var h4 = document.createElement("h4");
                    h4.setAttribute("class", "mb-2");
                    h4.append(document.createTextNode("Result " + parseInt(index+1, 10)));

                    let p = document.createElement("P");

                    p.insertAdjacentHTML('afterbegin', '<b>Description: </b>'+ description +'<br/>');
                    p.insertAdjacentHTML('afterbegin', '<b>Avg Rating: </b>'+ rating +'/5<br/>');
                    p.insertAdjacentHTML('afterbegin', '<b>Author: </b>'+ author +'<br/>');
                    p.insertAdjacentHTML('afterbegin', '<b>Title: </b>&quot;'+ title +'&quot;<br/>');

                    document.getElementById("bookInfoResults").appendChild(h4);
                    document.getElementById("bookInfoResults").appendChild(p);
                });
            }
        }
    };
    xmlhttp.setRequestHeader('Content-Type', 'text/xml');
    xmlhttp.send(xml);
};


function defaultRequest(){
    var exampleText= "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
        "    <Body>\n" +
        "        <GetBooksInfosByTitlesRequest xmlns=\"http://project1.ericss.com/bookInfo\">\n" +
        "            <titleList>\n" +
        "                <title>The Way of Kings</title>\n" +
        "            </titleList>\n" +
        "        </GetBooksInfosByTitlesRequest>\n" +
        "    </Body>\n" +
        "</Envelope>";
    document.getElementById('xmlText').innerHTML = exampleText;
};

function createResultHeader(){
    var h2 = document.createElement("h2");
    h2.setAttribute("class", "mb-3");
    h2.append(document.createTextNode("Results"));

    var resultDiv = document.createElement("div");
    resultDiv.setAttribute("id", "bookInfoResults");
    document.getElementById("bookInfoResultDiv").appendChild(h2);
    document.getElementById("bookInfoResultDiv").appendChild(resultDiv);
}

// The noted answer here helped me with this code
// https://stackoverflow.com/questions/9899372/pure-javascript-equivalent-of-jquerys-ready-how-to-call-a-function-when-t
function docReady(fn) {
    // see if DOM is already available
    if (document.readyState === "complete" || document.readyState === "interactive") {
        // call on next available tick
        setTimeout(fn, 1);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
}

docReady(defaultRequest());
