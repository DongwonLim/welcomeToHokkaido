// write.js 파일
document.addEventListener('DOMContentLoaded', function() {
    // 선택된 이미지들을 저장할 배열
    let selectedFiles = [];
    let removedFiles = [];
    
    const selectedImagesDiv = document.getElementById('selectedImages');
    const imageUpload = document.getElementById('imageUpload');
    const uploadForm = document.getElementById('uploadForm');
    
    if (imageUpload) { // null 체크 추가
        // 이미지 선택 이벤트 리스너
        imageUpload.addEventListener('change', function(e) {
            if (this.files.length) {
                // 새로 선택한 파일을 배열에 추가
                const file = this.files[0];
                
                // 이미지 미리보기 생성
                const reader = new FileReader();
                reader.onload = function(e) {
                    const imageId = 'img_' + Date.now(); // 고유 ID 생성
                    
                    // 이미지 컨테이너 생성
                    const imageContainer = document.createElement('div');
                    imageContainer.className = 'selected-image-item';
                    imageContainer.id = imageId;
                    
                    // 이미지 요소 생성
                    const img = document.createElement('img');
                    img.src = e.target.result;
                    
                    // 삭제 버튼 생성
                    const removeBtn = document.createElement('button');
                    removeBtn.className = 'remove-image';
                    removeBtn.innerHTML = 'X';
                    removeBtn.onclick = function() {
                        // 이미지 삭제
                        const indexToRemove = selectedFiles.findIndex(item => item.id === imageId);
                        if (indexToRemove !== -1) {
                            removedFiles.push(selectedFiles[indexToRemove].file.name);
                            selectedFiles.splice(indexToRemove, 1);
                            document.getElementById(imageId).remove();
                            if (document.getElementById('removedImages')) {
                                document.getElementById('removedImages').value = JSON.stringify(removedFiles);
                            }
                        }
                        return false;
                    };
                    
                    // 요소들을 컨테이너에 추가
                    imageContainer.appendChild(img);
                    imageContainer.appendChild(removeBtn);
                    if (selectedImagesDiv) {
                        selectedImagesDiv.appendChild(imageContainer);
                    }
                    
                    // 파일과 ID를 배열에 저장
                    selectedFiles.push({
                        id: imageId,
                        file: file
                    });
                };
                
                reader.readAsDataURL(file);
                
                // 파일 선택 필드 초기화 (같은 파일을 연속해서 선택할 수 있도록)
                this.value = '';
            }
        });
    } else {
        console.error("Element with ID 'imageUpload' not found!");
    }
    
    // 글로벌 함수로 prepareSubmit 등록
    window.prepareSubmit = function() {
        if (!uploadForm) {
            console.error("Form element not found!");
            return;
        }
            // `imageUpload`의 files에 선택한 파일들 할당
            const dataTransfer = new DataTransfer();
            selectedFiles.forEach((item) => {
                dataTransfer.items.add(item.file);
            });

            // 기존 파일 업로드 input에 수정된 파일 목록 적용
            if (imageUpload) {
                imageUpload.files = dataTransfer.files;
            }
    };
});