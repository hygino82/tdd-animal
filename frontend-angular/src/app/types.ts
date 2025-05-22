export type AnimalRequest = {
  name: string,
  age: number,
  animalType: string
};

export type AnimalResponse = {
  id: number,
  name: string,
  age: number,
  animalType: string
};

export type AnimalPage = {
  content: AnimalResponse[],
  totalElements: number,
  totalPages: number,
  last: boolean,
  size: number,
  number: number
}
