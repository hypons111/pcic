export interface IrecUserProjectCode {
  userId?: string;
  chName?: string;
  orgId?: string;
  orgName?: string;
  workTel?: string;
  email?: string;
  roleId?: string;
  roleName?: string;
  status?: string;
}

export class RecUserProjectCode implements IrecUserProjectCode {
  constructor(
    public userId?: string,
    public chName?: string,
    public orgId?: string,
    public orgName?: string,
    public workTel?: string,
    public email?: string,
    public roleId?: string,
    public roleName?: string,
    public status?: string
  ) {}
}
