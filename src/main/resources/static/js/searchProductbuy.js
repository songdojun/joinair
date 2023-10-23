// 초기화 함수 (이 함수를 호출하여 페이지가 로드될 때 검색을 초기화)
function initializeSearch() {
    document.getElementById("searchButton").addEventListener("click", function () {
        performSearch();
    });

    document.getElementById("searchKeyword").addEventListener("keyup", function (event) {
        if (event.key === "Enter") {
            event.preventDefault(); // 기본 엔터 행동 막기
            performSearch();
        }
    });
}

// 검색 함수
function performSearch(page = 0) {
    const currentSearchKeyword = document.getElementById("searchKeyword").value;

    // Ajax 요청을 통해 검색 결과를 가져옵니다.
    const url = "/productbuy/list";
    const params = new URLSearchParams({
        "searchOption": "Pro_Name", // 상품명으로 고정
        "searchKeyword": currentSearchKeyword,
        "page": page
    });
    const fullUrl = url + "?" + params.toString();

    // Ajax 요청을 통해 검색 결과를 가져옵니다.
    fetch(fullUrl)
        .then(response => response.text())
        .then(data => {
            // 검색 결과를 받은 후, .layout 요소에 추가합니다.
            const layout = document.querySelector(".layout");
            layout.innerHTML = data; // 검색 결과로 레이아웃을 업데이트

            // 검색 결과를 받은 후 다시 초기화하여 검색을 가능하게 합니다.
            initializeSearch();
        })
        .catch(error => console.error("검색 오류: ", error));
}

// HTML 파일이 로드될 때 초기화 함수 호출
document.addEventListener("DOMContentLoaded", function () {
    initializeSearch();
});
