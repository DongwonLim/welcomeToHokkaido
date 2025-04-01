function uploadFiles() {
    const input = document.getElementById('imageUpload');
    const files = input.files;

    if (files.length === 0) {
        alert('업로드할 파일이 없습니다!');
        return;
    }

    let formData = new FormData();

    Array.from(files).forEach(file => {
        formData.append("images", file);
    });
    fetch("/restaurant/write", {
        method: "POST",
        body: formData
    })
      .then(response => response.text())
      .then(data => {
          console.log("업로드 완료: ", data);
          alert("파일 업로드 성공!");
          selectedFiles = [];
      })
      .catch(error => console.error("업로드 실패: ", error));
}
