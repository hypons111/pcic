const line = `
GEO_COMPANY_ID
COMPANY_NAME
COMPANY_TYPE
COMPANY_ID
CONTACT_PERSON
IS_APPROVE
WORK_TEL
EMAIL
REMARK
APPLY_DATE
MOBILE_TEL
UPDATE_TIME
UPDATE_USER
CREATE_TIME
CREATE_USER
TRANSFER_FROM
TRANSFER_TIME
`;

const data = line.split('\n');
const snakeToCamel = string => {
  // string = string.toLowerCase();
  return string.toLowerCase().replace(/(_\w)/g, m => m[1].toUpperCase());
};

const camelCase = data.map(e => snakeToCamel(e));
// jdl用
console.log(camelCase.toString().replace(/,/g, '\n'));

// formDefault用
// console.log(camelCase.toString().replace(/,/g, ": '',\n"));

// i18n.json用
// console.log(camelCase.toString().replace(/\s/g,'": "').replace(/,/g,'：",\n"'));

// formOptions用
// console.log(camelCase.toString().replace(/\s/g,"_").replace(/,/g,"' },\n{ value: '").replace(/_/g,"', text: '"));
