let token = document.querySelector("meta[name='_csrf']").getAttribute("content");

function changeInput(e, articleId, replyId){

    const originValue = e.parentElement.childNodes[1].innerHTML;
    const lastIndex = e.parentElement.childNodes.length;
    const status = e.innerHTML;

    if(status === "수정"){

        e.innerHTML = "수정완료";

        const cancelBtn = document.createElement('button');
        cancelBtn.innerHTML = "수정취소";
        cancelBtn.onclick = toggleBtn;

        const inputTag = document.createElement('input');
        inputTag.value = originValue;

        e.parentElement.childNodes[1].remove();
        e.parentElement.insertBefore(inputTag, e.parentElement.childNodes[1]);
        e.parentElement.insertBefore(cancelBtn, e.parentElement.childNodes[lastIndex])

    } else if(e.innerHTML === "수정완료"){

        const inputValue = e.parentElement.childNodes[1].value;

        console.log(inputValue)

        let data = {
            method : "POST",
            body : JSON.stringify(
                {
                    updateValue : inputValue
                }
            ),
            header : {
                'Content-Type' : 'application/json',
                'X-CSRF-TOKEN' : token,
            }

        }

        fetch("http://localhost:8085/articles/" + articleId + "/replies/" + replyId + "/modify", data)
        .then(
            (response) => {
                alert("성공적으로 수정되었습니다.");
                window.location.reload();
            }
        )
        .catch(
            (error) => {
                alert("수정에 실패하였습니다.");
                window.location.reload();
            }
        )

    }


}

function toggleBtn(){
    window.location.reload();
}