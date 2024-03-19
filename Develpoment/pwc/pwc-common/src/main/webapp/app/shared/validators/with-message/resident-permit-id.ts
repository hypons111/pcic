import nationalId from '@/shared/validators/raw/resident-permit-id';

export default {
  $validator: nationalId,
  $message: '請輸入正確的居留證字號',
};
