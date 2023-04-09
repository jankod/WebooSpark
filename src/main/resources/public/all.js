console.log("dela 434 " + new Date())

window.addEventListener("load", event => {
    $("formNE").on("submit", function (formEvent) {
        formEvent.preventDefault()
        const $form = $(formEvent.target);
        let data = $form.serialize();
        console.log("data", data);
       // data = getFormData($form);
        //console.log("data 2", data);
        //data.return_type = "json"; // optional identifier - you can handle it on server and respond with JSON instead of HTML output
        $.ajax({
            url: $form.attr('action') || document.URL, // server script from form action attribute or document URL (if action is empty or not specified)
            type: $form.attr('method') || 'get', // method by form method or GET if not specified
            dataType: 'json', // we expect JSON in response
            // data: JSON.stringify(data)
            data: data
        }).done(function (respond) {
            console.log("data handled on server - response:", respond);
            // your code (after saving)

        }).fail(function (err) {
            alert("Server connection failed! "+ err);
        });

        return false; // suppress default submit action

    });

});

function getFormData($form) {
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}
