## 設計方法：
 ### 資料
 1. 定義一個 dataLoader 的 interface，其 body 包含一個 loadData() 的 function。
 2. 命名一個 JsonDataLoader 的 Class，並讓這個 Class 實作 dataLoader 這個 interface，同時 override loadData()。
 3. loadData() 的 function body 中，透過 inputStream、BufferedReader 將置於 assets 資料夾中的 Json file 中的內容取出。

---

 ### 架構
 1. 採用 MVVM 架構，在 viewModel 中 new 出所以定義的 JsonDataLoader。
 2. viewModel 中另定義兩個 function：getCourse()、getCourseList()。
  - getCourse()：透過 JsonDataLoader 的 loadData() 將 Json file 內容取出，並透過 Moshi 轉換為 kotlin 物件，將轉換後的結果存於變數。
  - getCourseList()：將暫存於變數中的 Course 變數，處理為可讓 recyclerView adapter 使用的 list，並利用 LiveData 的 Observer pattern 即時將這包整理後的資料送給 adapter。

---

 ### 資料呈像邏輯
<img width="300" alt="appScreen" src="https://github.com/ElvenChen/hahow-recruit-project/assets/134199087/dd877147-42de-4c83-98b3-2498aade4b41">

---

  1. Json file 中的 "status" key：
  - 值為 "INCUBATING" 時：畫面呈現 "募資中" ，並且其標籤底色為 "橘色" 。
  - 值為 "PUBLISHED" 時：畫面呈現 "已開課" ，並且其標籤底色為 "亮綠色" 。
  - 值為 "SUCCESS" 時：畫面呈現 "未開課" ，並且其標籤底色為 "暗綠色" 。

---

  2. Json file 中的 "numSoldTickets" key，以及 "successCriteria" 中的 "numSoldTickets" key：
  - "successCriteria" 中的 "numSoldTickets" key 為 "募資達標標準" 。
  - 與 "successCriteria" 同層的 "numSoldTickets" key 為 "當前募資數量" 。
  - 利用上述兩個數量決定 "進度條" 的完成進度呈現。
  - 利用上述兩個數量決定 "進度條上的文字" 的文字呈現：
    - "當前募資數量" 為 0 時：顯示 "0%" 。
    - "當前募資數量" 為大於 0 ，但小於 "募資達標標準" 時：顯示 "當前募資數量 / 募資達標標準 人" ；進度條顏色為 "橘色"。
    - "當前募資數量" 為大於 "募資達標標準" 時：顯示 "100%" ；進度條顏色為 "亮綠色"。

---

  3. Json file 中的 "proposalDueTime" key：
  - 當課程中沒有 "proposalDueTime" 時：不顯示倒數時間。
  - 當課程中有 "proposalDueTime" ，並且符合下列狀況：
    - "proposalDueTime" 已經超過當前時間：顯示 "倒數0天" 。
    - "proposalDueTime" 距離當前時間，小於 14天：顯示 "倒數X天" 。
    - "proposalDueTime" 距離當前時間，大於 14天：不顯示倒數時間。

---

 ### 其他
   1. 實作下拉螢幕可刷新資料功能。
   2. Git flow
