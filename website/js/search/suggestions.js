const getSuggestions = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/product')
            .then( async result => {

                const data = await result.json();
                resolve(data);

            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

var suggestions = [];

getSuggestions()
    .then(suggestion => {
        suggestion.forEach(element => {
            suggestions.push({id: element.id, name: element.name})
        });
        console.log(suggestions);
    })
