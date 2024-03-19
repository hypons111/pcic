function mrpUitl() {
  return {
    addZero(dateString) {
      const dateArray = dateString.split('/');
      dateArray.forEach((item, i) => {
        dateArray[i] = dateArray[i].padStart(2, '0');
      });
      return dateArray.join('/');
    },

    toY3(dateString) {
      const yyyy = dateString.substring(0, 4);
      const newdateString = this.addZero(dateString.replace(yyyy, Number(yyyy) - 1911));
      return newdateString;
    },

    toY4(dateString) {
      let yyy = dateString.substring(0, 3);
      return dateString.replace(yyy, Number(yyy) + 1911).replaceAll("/", "-");
    },

    idGenerator() {
      let d = new Date();
      const id =
        d.getFullYear().toString() +
        (d.getMonth() + 1).toString().padStart(2, '0') +
        d.getDate().toString().padStart(2, '0') +
        d.getHours().toString().padStart(2, '0') +
        d.getMinutes().toString().padStart(2, '0') +
        d.getSeconds().toString().padStart(2, '0');
      return id;
    },

    fullDateWithLastYear(string, p) {
      const dateParts = string.split(p);
      return `${dateParts[0] - 1}${p}${dateParts[1]}${p}${dateParts[2]}`;
    },

    fullDateWithLastMonth(string, p) {
      const dateParts = string.split(p);
      if (dateParts[1] === '1' || dateParts[1] === '01') {
        return `${dateParts[0] - 1}${p}12${p}${dateParts[2]}`;
      } else {
        return `${dateParts[0]}${p}${dateParts[1] - 1}${p}${dateParts[2]}`;
      }
    },

    compareDates(aDateString, bDateString, punctuation) {
      const aParts = aDateString.split(punctuation);
      const aDate = new Date(aParts[0], aParts[1] - 1, aParts[2]);
      const bParts = bDateString.split(punctuation);
      const bDate = new Date(bParts[0], bParts[1] - 1, bParts[2]);
      return aDate > bDate;
    },

    getMonthInterval(startDateString, endDateString) {
      const startDateArray = startDateString.split('/');
      const endDateArray = endDateString.split('/');
      let month = (Number(endDateArray[0]) - Number(startDateArray[0])) * 12;
      month -= Number(startDateArray[1]);
      month += Number(endDateArray[1]);

      if(month < 0) { return false; } 

      let startMonth = Number(startDateArray[1]);
      let intervalArray = [];
      for (let i = 0; i <= month; i++) {
        if (startMonth === 13) { startMonth = 1; }
        intervalArray.push(startMonth++);
      }
      
      return intervalArray;
    },

    getFullDataWithTheLastDayOfMonth(string, p) {
      const dateParts = string.split(p);
      const d = new Date((Number(dateParts[0]) + 1911), dateParts[1], 0);
      return `${d.getFullYear()}/${d.getMonth()+1}/${d.getDate()}`;
    }


  };
}

export default mrpUitl;
