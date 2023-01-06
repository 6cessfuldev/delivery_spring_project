document.getElementById('trigger').addEventListener('click', ()=> {
    document.getElementById('review_multiple').click();
});

const regExp = new RegExp("\.(exe|sh|bat|msi|dll|js)$");
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif)$");
const maxSize = 1024*1024*20; //20MB

function fileSizeValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }else if(!regExpImg.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

document.addEventListener("change", (e)=>{
    console.log("test");
    if(e.target.id == "review_multiple"){
        console.log("changed");
        document.getElementById('regBtn').disabled = false;
        const fileObject = document.getElementById('review_multiple').files;
        console.log(fileObject);

        let div = document.getElementById("image_container");

        if(isOk == 0){
           document.getElementById('regBtn').disabled = true;
        }
    }
})

  function setThumbnail(event) {
        var reader = new FileReader();

        reader.onload = function(event) {
          var img = document.createElement("img");
          img.setAttribute("src", event.target.result);
          document.querySelector("div#image_container").appendChild(img);
        };

        reader.readAsDataURL(event.target.files[0]);
      }


