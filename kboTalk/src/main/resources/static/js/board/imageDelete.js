function deleteImage(imageUrl){
    fetch("/api/board/deleteImage", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({imageUrl: imageUrl})
    })
    .then(response => {
        if(!response.ok) throw new Error("이미지 삭제 실패");
        return response.text();
    })
    .then(result => {
        console.log("이미지 삭제 성공: ", result);
    })
    .catch(error => {
        console.log("이미지 삭제 실패: ", result);
    });
}