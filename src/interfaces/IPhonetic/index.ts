interface IPhoneticSend {
  name: string;
}

interface IPhoneticResponse {
  id: string;
  name: string;
  code: string;
}

export type { IPhoneticResponse, IPhoneticSend };
