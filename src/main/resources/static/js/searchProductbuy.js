// HTML 파일이 로드될 때 초기화 함수 호출..
document.addEventListener("DOMContentLoaded", function () {
    initializeSearch();
    initializeCategoryButtons();
});

// 검색 함수
function performSearch(page = 0) {
    const currentSearchOption = document.getElementById("searchOption").value;
    const currentSearchKeyword = document.getElementById("searchKeyword").value;

    // Ajax 요청을 통해 검색 결과를 가져옵니다.
    const url = "/productbuy/list";
    const params = new URLSearchParams({
        "searchOption": currentSearchOption,
        "searchKeyword": currentSearchKeyword,
        "page": page
    });

    const fullUrl = url + "?" + params.toString();

    fetch(fullUrl)
        .then(response => response.text())
        .then(data => {
            // 검색 결과를 .layout 요소 안에 업데이트  중복 네브바 문제 해결완료
            const layout = document.querySelector(".layout");
            const searchResults = new DOMParser().parseFromString(data, "text/html");
            layout.innerHTML = searchResults.querySelector(".layout").innerHTML;

            // 검색 결과를 받은 후 다시 초기화하여 검색을 가능하게 합니다.
            initializeSearch();
            initializeCategoryButtons();
        })
        .catch(error => console.error("검색 오류: ", error));
}


// 카테고리 버튼 초기화 함수
function initializeCategoryButtons() {
    const categoryButtons = document.querySelectorAll(".category-button");

    categoryButtons.forEach(button => {
        button.addEventListener("click", function () {
            const category = button.getAttribute("data-category");
            document.getElementById("searchOption").value = "Cate_Name";
            document.getElementById("searchKeyword").value = category;
            performSearch();
        });
    });
}

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




