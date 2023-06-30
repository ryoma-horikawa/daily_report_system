/*
<script type="text/javascript">
var gTabldID = 'report_list';  // テーブルのエリアのIDを設定
var gSortNum = 1;              // 1 : 数値に見える列は数値でソート
var gSortAa  = 1;              // 1 : 英大文字と小文字を区別しない

var gSortBtnRow = 0;

window.onload = function() {
  tSortInit();
}

function tSortInit(){

  var wTABLE  = document.getElementById(gTabldID);
  var wTR     = wTABLE.rows;
  var wAddBtn = '';

  // --- テーブル内を検索してソートボタンを付ける ----------------
  for(var i=0; i < wTR.length; i++){

    var wTD     = wTABLE.rows[i].cells;
    for(var j=0; j < wTD.length; j++){

      if(wTD[j].getAttribute('cmanSortBtn') !== null){

        wAddBtn  = '<div class="tsImgArea">';
        wAddBtn += '<svg class="tsImg" id="ts_A_'+j+'" onclick="tSort(this)"><path d="M4 0 L0 6 L8 6 Z"></path></svg>';
        wAddBtn += '<svg class="tsImg" id="ts_D_'+j+'" onclick="tSort(this)"><path d="M0 0 L8 0 L4 7 Z"></path></svg>';
        wAddBtn += '</div>';

        wTD[j].innerHTML = wTD[j].innerHTML+wAddBtn;
      }
    }

    // --- ボタンを付けたら以降の行は無視する --------------------
    if(wAddBtn != ''){
      gSortBtnRow = i;
      break;
    }
  }
}

function tSort(argObj){
 //===============================================================
 //  ソート実行
 //===============================================================

   // 「ts_A_1」形式 [1]:A-昇順,D-降順  [2]:列番号
  var wSortKey  = argObj.id.split('_');

  var wTABLE    = document.getElementById(gTabldID);
  var wTR       = wTABLE.rows;
  var wItem     = [];              // クリックされた列の値
  var wItemSort = [];              // クリックされた列の値（項目ソート後）
  var wMoveRow  = [];              // 元の行位置（行削除考慮位置）
  var wNotNum   = 0;               // 1 : 数字でない
  var wStartRow = gSortBtnRow + 1; // ソートを開始する行はボタンの次の行

  // ------------------------------------------------------
  //  クリックされた列の値を取得する
  // ------------------------------------------------------
  for(var i=wStartRow; i < wTR.length; i++){
    var j = i - wStartRow;
    wItem[j] = wTR[i].cells[wSortKey[2]].innerText.toString();

    if(wItem[j].match(/^[-]?[0-9,\.]+$/)){
    }else{
        wNotNum = 1;
    }

  }
  // ソート用に配列をコピー
  wItemSort = wItem.slice(0, wItem.length);

  // ------------------------------------------------------
  //  列の値でソートを実行
  // ------------------------------------------------------
  if(wSortKey[1] == 'A'){
    if((gSortNum == 1)&&(wNotNum == 0)){
      wItemSort.sort(sortNumA);           // 数値で昇順
    }else{
      wItemSort.sort(sortStrA);           // 文字で昇順
    }
  }else{
    if((gSortNum == 1)&&(wNotNum == 0)){
      wItemSort.sort(sortNumD);           // 数値で降順
    }else{
      wItemSort.sort(sortStrD);           // 文字で降順
    }
  }

  // ------------------------------------------------------
  //  行の入れ替え順を取得
  //    ソート前後の列の値を比較して行の移動順を確定
  //    配列を削除して前詰めしている（移動時も同じ動き）
  // ------------------------------------------------------
  for(var i=0; i < wItemSort.length; i++){
     for(var j=0; j < wItem.length; j++){
      if(wItemSort[i] == wItem[j]){
          wMoveRow[i] = j + wStartRow;
          wItem.splice(j, 1);
          break;
      }
    }
  }

  // ------------------------------------------------------
  //  ソート順に行を移動
  // ------------------------------------------------------
  for(var i=0; i < wMoveRow.length; i++){


    var wMoveTr = wTABLE.rows[wMoveRow[i]];                  // 移動対象
    var wLastTr = wTABLE.rows[wTABLE.rows.length - 1];   // 最終行

    // 最終行にコピーしてから移動元を削除
    wLastTr.parentNode.insertBefore(wMoveTr.cloneNode(true), wLastTr.nextSibling);
    wTABLE.deleteRow(wMoveRow[i]);

  }

  // ------------------------------------------------------
  //  クリックされたソートボタンの色付け
  // ------------------------------------------------------
  var elmImg = document.getElementsByClassName('tsImg');
  for (var i=0; i < elmImg.length; i++) {

    if(elmImg[i].id == argObj.id){
      elmImg[i].style.backgroundColor = '#ffff00';
    }else{
      elmImg[i].style.backgroundColor = '';
    }

  }

}

function sortNumA(a, b) {
 //===============================================================
 //  数字のソート関数（昇順）
 //===============================================================
  a = parseInt(a.replace(/,/g, ''));
  b = parseInt(b.replace(/,/g, ''));
  return a - b;
}

function sortNumD(a, b) {
 //===============================================================
 //  数字のソート関数（降順）
 //===============================================================
  a = parseInt(a.replace(/,/g, ''));
  b = parseInt(b.replace(/,/g, ''));
  return b - a;
}

function sortStrA(a, b){
 //===============================================================
 //  文字のソート関数（昇順）
 //===============================================================
  a = a.toString();
  b = b.toString();
  if(gSortAa == 1){             // 1 : 英大文字小文字を区別しない
    a = a.toLowerCase();
    b = b.toLowerCase();
  }
  if     (a < b){ return -1; }
  else if(a > b){ return  1; }
  return 0;
}

function sortStrD(a, b){
 //===============================================================
 //  文字のソート関数（降順）
 //===============================================================
  a = a.toString();
  b = b.toString();
  if(gSortAa == 1){             // 1 : 英大文字小文字を区別しない
    a = a.toLowerCase();
    b = b.toLowerCase();
  }

  if     (b < a){ return -1; }
  else if(b > a){ return  1; }
  return 0;
}
</script>

*/

/*
sortableTable.setTable(document.querySelector('#report_list'));
 // set data to be sorted
 sortableTable.setData(data);
 // handling events
 sortableTable.events()
     .on('sort', (event) => {
       console.log(`[SortableTable#onSort]
     event.colId=${event.colId}
     event.sortDir=${event.sortDir}
     event.data=\n${JSON.stringify(event.data)}`);
     });
*/



/*
<script>
window.addEventListener('load', function () {
    let column_no = 0; //今回クリックされた列番号
    let column_no_prev = 0; //前回クリックされた列番号
    document.querySelectorAll('#report_list th').forEach(elm => {
        elm.onclick = function () {
            column_no = this.cellIndex; //クリックされた列番号
            let table = this.parentNode.parentNode.parentNode;
            let sortType = 0; //0:数値 1:文字
            let sortArray = new Array; //クリックした列のデータを全て格納する配列
            for (let r = 1; r < table.rows.length; r++) {
                //行番号と値を配列に格納
                let column = new Object;
                column.row = table.rows[r];
                column.value = table.rows[r].cells[column_no].textContent;
                sortArray.push(column);
                //数値判定
                if (isNaN(Number(column.value))) {
                    sortType = 1; //値が数値変換できなかった場合は文字列ソート
                }
            }
            if (sortType == 0) { //数値ソート
                if (column_no_prev == column_no) { //同じ列が2回クリックされた場合は降順ソート
                    sortArray.sort(compareNumberDesc);
                } else {
                    sortArray.sort(compareNumber);
                }
            } else { //文字列ソート
                if (column_no_prev == column_no) { //同じ列が2回クリックされた場合は降順ソート
                    sortArray.sort(compareStringDesc);
                } else {
                    sortArray.sort(compareString);
                }
            }
            //ソート後のTRオブジェクトを順番にtbodyへ追加（移動）
            let tbody = this.parentNode.parentNode;
            for (let i = 0; i < sortArray.length; i++) {
                tbody.appendChild(sortArray[i].row);
            }
            //昇順／降順ソート切り替えのために列番号を保存
            if (column_no_prev == column_no) {
                column_no_prev = -1; //降順ソート
            } else {
                column_no_prev = column_no;
            }
        };
    });
});
//数値ソート（昇順）
function compareNumber(a, b)
{
    return a.value - b.value;
}
//数値ソート（降順）
function compareNumberDesc(a, b)
{
    return b.value - a.value;
}
//文字列ソート（昇順）
function compareString(a, b) {
    if (a.value < b.value) {
        return -1;
    } else {
        return 1;
    }
    return 0;
}
//文字列ソート（降順）
function compareStringDesc(a, b) {
    if (a.value > b.value) {
        return -1;
    } else {
        return 1;
    }
    return 0;
}
</script>
*/