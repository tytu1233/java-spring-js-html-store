const searchWrapper = document.querySelector(".search-input");
const inputBox = searchWrapper.querySelector("input");
const suggBox = searchWrapper.querySelector(".autocom-box");
const icon = searchWrapper.querySelector(".icon");
let linkTag = searchWrapper.querySelector("a");
let webLink;

console.log(inputBox.onkeyup)
inputBox.onkeyup = (e)=>{
    
    let userData = e.target.value;
    let emptyArray = [{}];
    if(userData){
        console.log(userData)
        emptyArray = suggestions.filter((data)=>{
            return data.name.toLocaleLowerCase().startsWith(userData.toLocaleLowerCase());
        });
        emptyArray = emptyArray.map((data)=>{
            return data = `<li>${data.name}</li>`;
        });
        searchWrapper.classList.add("active");
        showSuggestions(emptyArray);
        let allList = suggBox.querySelectorAll("li");
        for (let i = 0; i < allList.length; i++) {
            allList[i].setAttribute("onclick", "select(this)");
        }
    }else{
        searchWrapper.classList.remove("active");
    }
}

const select = (element) =>{
    let selectData = element.textContent;
    inputBox.value = selectData;
    let obj = suggestions.find(o => o.name === selectData);

    icon.onclick = ()=>{
        webLink = `product_detail.html?product=${obj.id}`;
        linkTag.setAttribute("href", webLink);
        linkTag.click();
        obj = null;
    }
    searchWrapper.classList.remove("active");
}

const showSuggestions = (list) => {
    let listData;
    if(!list.length){
        userValue = inputBox.value;
        listData = `<li>${userValue}</li>`;
    }else{
      listData = list.join('');
    }
    suggBox.innerHTML = listData;
}