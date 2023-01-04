// javascript
const listEnd = document.querySelector("#endList");
const option = {
    root: null,
    rootMargin: "0px 0px 0px 0px",
    thredhold: 0,
}

const onIntersect = (entries, observer) => { 
    // entries는 IntersectionObserverEntry 객체의 리스트로 배열 형식을 반환합니다.
    entries.forEach(entry => {
        if(entry.isIntersecting){
            addList();

            const listWrap = document.querySelector(".registered");            
            // listWrap.insertAdjacentHTML("beforeend", `
            // `)
        }
    });
};

const observer = new IntersectionObserver(onIntersect, option);
observer.observe(listEnd);

let listCnt = 0;
function addList(){

    const list = document.querySelector(".registered");

    let add = "";
    getListMoreFromServer().then(result => {
        console.log(result.length);
        if(result!=null && result.length>0){
            console.log("add case");
            for(let i=0; i<result.length; i++){
                console.log(i);
                if(i%2==0){
                    add+=`<div class="one-row justify-content-center">`;
                }

                add+=`<div class="diner-card bg-white" id="diner-card">`;
                add+=`<div class="diner-img">`;
                add+=`<img src="/resources/source/dinerimg.PNG" alt="" width="80px" height="80px"></div>`;
                add+=`<div class="diner-body"><h5 class="diner-title">${result[i].diner_name}</h5>`;
                add+=`<p class="diner-text"><span class="score">★3.8</span> | 리뷰 1902 | 사장님댓글 791 </p>`;
                add+=`<p class="diner-text"><span class="del-option">${result[i].diner_method_pay}</span> |`;
                add+=`<span>${result[i].diner_min_pay}원 이상 배달</span></p>`;
                add+=`<p class="delivery-time"> 22분 </p></div></div>`;

                if(i%2==1 || i+1 == result.length){
                    add+=`</div>`;
                }
            }
            list.innerHTML+=add;
        }
    })

}

async function getListMoreFromServer(){

    try {
        let resp = await fetch("/diner/moreList");
        let result = resp.json();
        return result;

    } catch (error) {
        console.log(error);
    }
}