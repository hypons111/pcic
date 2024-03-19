<template>
  <div>
    <section class="container mt-2 mb-2">
      <div class="h4 m-0 py-2 button-border">常見問題</div>
      <div class="card mt-2">
        <div class="card-header text-left">
          <div class="align-items-center">
            <div class="col-sm-11 p-0">
              <h5 class="m-0">
                <button class="btn btn-info" @click="changeQuestionType(1, '基本問題')">基本問題</button>
                <button class="btn btn-info" @click="changeQuestionType(2, '功能問題')">功能問題</button>
                <button class="btn btn-info" @click="changeQuestionType(3, '共同問題')">共同問題</button>
                <button class="btn btn-info" @click="changeQuestionType(4, '操作問題')">操作問題</button>
              </h5>
            </div>
          </div>
        </div>

        <div class="card-body">

        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { useNotification } from '@/shared/notification';
import NotificationService from '@/shared/notification/notification-service';
import { onMounted } from '@vue/composition-api';

export default {
  data() {
    return {
      questionType: 2,
    };
  },

  setup() {
    const notificationService: NotificationService = useNotification();
    let jsonObject

    function getQuestionData() {
      const fakeData = `
      [
        {
          "questionType": "1",
          "questionId": "Q1",
          "question": "第二代價格資料庫與第一代之主要差異為何？",
          "answer": "第二代價格資料庫為第一代價格資料庫的優化版本，提供使用者更多查詢的條件進行更動態的計算與服務。此外，第二代價格資料庫提供了更多種類的查詢結果呈現方式，包含：地圖顯示、以工程編碼邏輯做樹狀顯示、以及價格走勢趨勢圖等多種查詢服務方式。"
        },
        {
          "questionType": "1",
          "questionId": "Q2",
          "question": "第二代價格資料庫有提供會員服務與專屬功能，申請會員的條件為何？",
          "answer": "第二代價格資料庫推動的會員制度，出發點在於分析不同使用者的使用行為模式，進而設計出更好用的服務。申請會員沒有任何資格限制，也不需付費，會員資料僅作為本會分析使用，不對外提供做為任何用途。成為會員最大的好處在於可建立個人化的常用批次查詢，可以一次查詢多筆使用者最常需要查詢的工項價格。"
        },
        {
          "questionType": "1",
          "questionId": "Q3",
          "question": "第二代價格資料庫所提供資料是否會依物價指數漲跌而做調整?",
          "answer": "第二代價格資料庫提供使用者自訂時間區間查詢的功能，為避免不同查詢時間點以相同自訂時間區間條件查詢之結果產生差異，第二代價格資料庫不提供依物價指數調整後的價格資訊，使用者可依需求自行計算調整。"
        },
        {
          "questionType": "1",
          "questionId": "Q4",
          "question": "第二代價格資料庫是否有提供工項資料，或是以資源項目為主？",
          "answer": "考量使用者查詢需求，第二代價格資料庫雖仍以資源項目為主，但有提供部分組合工項，惟尚無提供其下層單價分析內容，建議使用者依據個案單價分析資源項目及功率內容推估組合工項單價。"
        },

        {
          "questionType": "1",
          "questionId": "Q5",
          "question": "第一代價格資料庫所提供的價格為決標價回除預算標比所得之近似預算價格，第二代價格資料庫所提供的資料是否相同。",
          "answer": "第二代價格資料庫所提供的資料分為「預算價格」與「決標價格」，係分別以預算及契約單價原始資料檔進行統計，不再回除標比。使用者可依照需求，查詢預算或契約價格。另因受限於回收資料正確性，並非每個項目都可查詢到預算及契約兩種價格。另外，樣本數較少時，使用者應注意其平均價格與標準差兩者數據之差異，不宜逕行參考引用。"
        },
        {
          "questionType": "1",
          "questionId": "Q6",
          "question": "第二代價格資料庫提供內容與更新時間為何？ ",
          "answer": "第二代價格資料庫將於每月10日更新，提供經回收處理完成之歷史標案資料。以105年7月的資料為例，主辦機關應於決標後一個月內繳交，因此該月的資料將於8月底回收，經處理後於105年9月10日更新資料庫內容。"
        },
        {
          "questionType": "2",
          "questionId": "Q1",
          "question": "第二代價格資料庫所查詢的結果是否可以下載？",
          "answer": "使用者在第二代價格資料庫所查詢到的資料，不論是列表資料，或者價格走勢圖，皆可下載。價格資料以excel檔案格式提供下載，使用者可視需求進行相關分析。"
        },
        {
          "questionType": "2",
          "questionId": "Q2",
          "question": "第二代價格資料庫是否可使用工程編碼進行查詢？",
          "answer": "可以，第二代價格資料庫可使用工程編碼及關鍵字進行查詢。"
        },
        {
          "questionType": "2",
          "questionId": "Q3",
          "question": "如何儲存個人常常需要查詢的工項價格？",
          "answer": "第二代價格資料庫建議使用者可申請成為會員，申請成為會員後，使用者可以在每次查詢過程中，建立自己常用的查詢集合，儲存後在使用者每次登入皆可使用儲存過的查詢集合。常用查詢使用說明，請下載及參考網站右上角的使用手冊，謝謝。"
        },
        {
          "questionType": "2",
          "questionId": "Q4",
          "question": "第二代價格資料庫所提供的自動關鍵字建議，如何使用？",
          "answer": "使用者於第二代價格資料庫輸入兩個字或以上，系統就會依輸入關鍵字推薦查詢項目，提供使用者選擇，以幫助使用者更方便及正確的輸入查詢關鍵字。所推薦查詢項目，主要以常被查詢的熱門項目排在最上方，再輔以其他能夠查詢到資料的項目。使用小提醒，使用者如果選擇完關鍵字後，又進行了查詢條件的調整(如：決標時間、工程規模)，則可能會造成原先選擇的關鍵字無法查到資料。"
        },
        {
          "questionType": "2",
          "questionId": "Q5",
          "question": "為何查詢時間區間只能查詢到2015/07到現在的資料，無法查詢到更久之前的資料？",
          "answer": "目前第二代價格資料庫所提供服務的資料區間為104年7月起迄今的價格資料。至於104年7月之前的歷史單價資料，使用者可透過第一代價格資料庫進行查詢。"
        },
        {
          "questionType": "2",
          "questionId": "Q6",
          "question": "上傳XML檔案格式之注意事項為何？",
          "answer": "請上傳由PCCES產出之標準XML檔案或者依照PCCES之檔案格式產出的XML檔案，上傳時須同時提供預算與契約單價的XML檔案，方可完成上傳，請不要僅上傳一個檔案或者標單檔案。此外，如果是以outlook進行XML檔案傳輸容易造成檔案錯誤，建議以壓縮檔案傳輸。"
        },
        {
          "questionType": "3",
          "questionId": "Q1",
          "question": "公共工程價格資料庫中資源項目之價格與工作項目之價格應如何區分？",
          "answer": "公共工程價格資料庫之價格分為工作項目之價格、資源項目之價格，其中資源項目之價格包含(M)材料類、(L)人力類、(E)機具類，因此工作項目之價格已包含(M)材料、(E)機具、(L)人工以及(W)雜項的價格組成，其工作項目以及資源項目之編碼以可以區別運用。"
        },
        {
          "questionType": "3",
          "questionId": "Q2",
          "question": "公共工程價格資料庫中之價格資訊何時更新？",
          "answer": "目前公共工程價格資料庫每三~四個月更新一期價格資訊，另每兩週(每月15與25日)更新大宗資材項目，請參考公共工程價格資料庫之網站（<a href='http://www.pcc.gov.tw' target='_blank'>http://www.pcc.gov.tw/工程技術/工程技術整合/公共工程價格資料庫</a>）。"
        },
        {
          "questionType": "3",
          "questionId": "Q3",
          "question": "公共工程價格資料庫中每兩週更新之價格資訊有那些？",
          "answer": "目前公共工程價格資料庫每兩週更新之價格資訊，包含03310結構用混凝土、M030504預拌混凝土材料費、M030502粒料、024620 H型鋼樁、0321030 鋼筋，SD280，連工帶料、0321050 鋼筋，SD420，連工帶料、M03210 鋼筋等大宗資材之工作項目及資源項目之資訊，供使用者參考。"
        },
        {
          "questionType": "3",
          "questionId": "Q4",
          "question": "因應營建物價波動，是否可於網站公布材料單價以應時需？",
          "answer": "目前大宗資材每兩週(每月15與25日)皆更新價格並公佈於公共工程技術資料庫網站上，其他材料價格亦每三~四個月更新一次並公佈於網站上，且價格資訊下載後可轉換至PCCES資料庫中。"
        },
        {
          "questionType": "3",
          "questionId": "Q5",
          "question": "公共工程價格資料庫之參考單價是否可依據不同工程規模而提供不同的價格？",
          "answer": "公共工程價格資料庫，其主要目的為提供工作項目、人力、機具以及材料方面之參考價格資訊，並且提供工程主辦機關執行預算編列時之參考依據，實際影響價格之因素（如工程規模、地區、工法、市場競爭等）甚多，請使用者在運用時，考量前述因素再進行調整。"
        },
        {
          "questionType": "3",
          "questionId": "Q6",
          "question": "公共工程價格資料庫中之價格資訊可否從PCCES中查詢？",
          "answer": "目前公共工程價格資料庫每期公佈之價格資訊除公共工程價格資料庫網站中可以查詢外，每期公佈時並同步於PCCES中可以查詢，下載查詢資料於（<a href='http://www.pcc.gov.tw' target='_blank'>http://www.pcc.gov.tw/工程技術/工程技術整合/公共工程價格資料庫/公共工程價格資料庫下載供PCCES載入後使用</a>）。"
        },
        {
          "questionType": "3",
          "questionId": "Q7",
          "question": "公共工程價格資料庫中之價格資訊可否下載？",
          "answer": "目前公共工程價格資料庫中的價格資訊尚未提供下載服務，為提供使用者多樣性的查詢功能，公共工程價格資料庫每期公佈時並同步於PCCES中可以查詢，下載查詢資料於（<a href='http://www.pcc.gov.tw' target='_blank'>http://www.pcc.gov.tw/工程技術/工程技術整合/公共工程價格資料庫/公共工程價格資料庫下載供PCCES載入後使用</a>）。"
        },
        {
          "questionType": "3",
          "questionId": "Q8",
          "question": "公共工程價格資料庫之各期價格，日後是否仍可追溯查詢？",
          "answer": "目前公共工程價格資料庫之各期價格皆公布於網站中，使用者可依需求查詢各期價格資訊，另每兩週(每月15與25日)更新之大宗資材價格資訊，僅提供最新資訊，追溯部份之價格則無法查詢。"
        },
        {
          "questionType": "3",
          "questionId": "Q9",
          "question": "公共工程價格資料庫內的價格是市場行情價還是預算編列的價錢？",
          "answer": "公共工程價格資料庫是依據標案資料統計分析並依價格資料庫運作機制而來，主要提供主辦機關於發包預算時參考使用，但實際使用時仍須依據該工程之特性、環境與現況進行修正。"
        },
        {
          "questionType": "3",
          "questionId": "Q10",
          "question": "如何查詢公共工程價格資料庫之價格資訊？",
          "answer": "請參考公共工程價格資料庫之網站（<a href='http://www.pcc.gov.tw' target='_blank'>http://www.pcc.gov.tw/工程技術/工程技術整合/公共工程價格資料庫</a>）或（<a href='http://pcces.pcc.gov.tw/' target='_blank'>http://pcces.pcc.gov.tw/</a>），點選或下載「使用說明」，便能依據使用說明之步驟搜尋所需之相關資料。"
        },
        {
          "questionType": "3",
          "questionId": "Q11",
          "question": "公共工程價格資料庫之法令依據為何？",
          "answer": "依據政府採購法第十一條內容規定，建立公共工程價格資料庫提供各界參考應用。"
        },
        {
          "questionType": "3",
          "questionId": "Q12",
          "question": "價格資料庫內是否有提供人力價格資訊？如工程顧問服務業人力。",
          "answer": "1. 公共工程價格資料庫查詢網址為<a href='http://pcces.pcc.gov.tw/' target='_blank'>http://pcces.pcc.gov.tw/</a>公共工程價格資料庫，查詢方式分為工項編碼與關鍵字查詢，工項編碼查詢方式若使用者知悉該項目之編碼則可使用該查詢方法，關鍵字查詢方法則直接輸入所需查詢之關鍵字即可，人力部份可直接於編碼處輸入00000，即可查詢所有人力價格。<br>2. 另101年底已於大宗資材市場價格趨勢中提供工程顧問服務業人力價格，使用者皆可參考。"
        },
        {
          "questionType": "4",
          "questionId": "Q1",
          "question": "如何查詢公共工程價格資料庫網頁？",
          "answer": "可連結至行政院公共工程委員會網頁後，進入「工程技術」之頁面，即可連結至公共工程價格資料庫網頁（<a href='http://www.pcc.gov.tw' target='_blank'>http://www.pcc.gov.tw/工程技術/工程技術整合/公共工程價格資料庫</a>）。或經由（<a href='http://pcces.pcc.gov.tw/' target='_blank'>http://pcces.pcc.gov.tw/</a>）網址進入公共工程技術資料庫網站後，點選價格資料庫功能。"
        },
        {
          "questionType": "4",
          "questionId": "Q2",
          "question": "於公共工程價格資料庫網頁中，應如何找尋所需之相關資料？",
          "answer": "本價格資料庫網頁設有搜尋功能：請於網站首頁右上方輸入欲查詢之關鍵字，便能搜尋所需之相關資料。"
        },
        {
          "questionType": "4",
          "questionId": "Q3",
          "question": "公共工程價格資料庫搜尋網頁若出現錯誤訊息，應如何排除？",
          "answer": "若IE瀏覽器的隱私權設定調到「高」或「封鎖所有Cookie」,則程式因無法存取Seesion就會出現錯誤訊息,請調整使用電腦IE瀏覽器的隱私權設定，方可解決相關錯誤訊息問題，若持續發生，請來電詢問，將有專人為您解決。"
        },
        {
          "questionType": "4",
          "questionId": "Q4",
          "question": "如何查詢天花板價格？",
          "answer": "公共工程價格資料庫查詢網址為<a href='http://pcces.pcc.gov.tw/' target='_blank'>http://pcces.pcc.gov.tw/公共工程價格資料庫</a>，查詢方式分為工項編碼與關鍵字查詢，工項編碼查詢方式若使用者知悉該項目之編碼則可使用該查詢方法，關鍵字查詢方法則直接輸入所需查詢之關鍵字即可。"
        },
        {
          "questionType": "4",
          "questionId": "Q5",
          "question": "如何於網頁中查詢大宗資材趨勢？",
          "answer": "告知使用者於價格資料庫左手編選項選取大宗資材市場調查趨勢即可查詢到砂石、鋼筋、預拌混凝土等大宗資材近兩週價格趨勢，並有近兩年來價格趨勢圖可提供參考。"
        }
      ]
      `;
      jsonObject = JSON.parse(fakeData);

      showData(jsonObject, "1", "基本問題")
    }

    function showData(data, type, typeString) {
      const questionData = data.filter(d => d.questionType == type)
      let string = `
        <table>
          <thead>
            <tr><th colspan='2'>${typeString}</th></tr>
          </thead>
        <tbody>
      `
      questionData.forEach(d => {
        string += `
          <tr>
            <td style="vertical-align: top;"><b>${d.questionId}:</b></td>
            <td style="vertical-align: top; padding: 0px 0px 10px 30px;">${d.question}</td>
          </tr>
          <tr>
            <td style="vertical-align: top;"><b>Ans:</b></td>
            <td style="vertical-align: top; padding: 0px 30px 40px 30px;">${d.answer}</td>
          </tr>
        `
      })
      string += `</tbody></table>`
      document.querySelector(".card-body").innerHTML = string
    }

    function changeQuestionType(type, typeString) {
      this.questionType = type;
      showData(jsonObject, type, typeString)
    }

    onMounted(() => {
      getQuestionData();
    });

    return {
      getQuestionData,
      changeQuestionType,
    };
  },
};
</script>

<style scoped>
@import 'mrp.css';

* {
  /* font-size: 1.1rem; */
}

td[colspan='2'] {
  font-size: 1.2rem;
  font-weight: bold;
  padding: 30px 0px 1px 0px;
}

tr td:first-child {
  font-weight: bold;
  vertical-align: top;
  padding-right: 30px;
}

td {
  padding: 10px 0px;
}

.red {
  border: 1px solid red;
}

</style>
