
document.getElementById('submitButton').addEventListener('click', function() {
    // Get the value from the input field
    const inputValue = document.getElementById('inputField').value;
    console.log(inputValue)

    // Create a JSON payload
    const payload = {
        content: inputValue + "\nWrite the entire answer as a markdown formatted block. Without actually using the three ticks",
        name: "TTT"
    };

    // Send the payload to an example API
    fetch('http://127.0.0.1:7000/gpt/3', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
        .then(response => response.json())
        .then(data => {
            // Display the response in the immutable text field
            // document.getElementById('responseField').value = JSON.stringify(data, null, 2);
            let datumElementElementElement = data['choices'][0]['message']['content'];
            console.log(datumElementElementElement)
            document.getElementById('responseField').innerHTML = marked.parse(datumElementElementElement)
            // document.getElementById('responseField').innerHTML = marked.parse("# hello\n- [ ] This is me")
        })
        .catch(error => {
            console.error('Error:', error);
        });
});
