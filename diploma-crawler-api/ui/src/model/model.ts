export enum AnalysePhase {
  ANALYSE_START = "Анализ начат",
  ANALYSE_FINISHED = "Анализ закончен",
  LEMMATIZATION = "Стадия лемматизации",
  NORMALIZATION = "Стадия нормализации",
  ANALYSE_FAILED = "Ошибка анализа",
}

export class DomainObject {
  private _id: string;
  private _creationTime: Date;
  private _lastModifiedTime: Date;
  private _ts: number;

  constructor(id: string, creationTime: Date, lastModifiedTime: Date, ts: number) {
    this._id = id;
    this._creationTime = creationTime;
    this._lastModifiedTime = lastModifiedTime;
    this._ts = ts;
  }

  public get tableHeaders(): Array<string> {
    return ["ID", "Creation Time", "Last Modified Time"]
  }

  get id(): string {
    return this._id;
  }

  set id(value: string) {
    this._id = value;
  }

  get creationTime(): Date {
    return this._creationTime;
  }

  set creationTime(value: Date) {
    this._creationTime = value;
  }

  get lastModifiedTime(): Date {
    return this._lastModifiedTime;
  }

  set lastModifiedTime(value: Date) {
    this._lastModifiedTime = value;
  }

  get ts(): number {
    return this._ts;
  }

  set ts(value: number) {
    this._ts = value;
  }
}


export class WebPortal extends DomainObject {
  private _portalName: string;
  private _domainName: string;
  private _webPages: Array<{ id: string, name: string }>;

  constructor(id: string, creationTime: Date, lastModifiedTime: Date, ts: number, portalName: string, domainName: string, webPageIds: Array<{ id: string; name: string }>) {
    super(id, creationTime, lastModifiedTime, ts);
    this._portalName = portalName;
    this._domainName = domainName;
    this._webPages = webPageIds;
  }

  static get tableHeaders(): Array<string> {
    return ["ID", "Portal Name", "Domain Name", "Creation Time", "Last Modified Time"]
  }

  get portalName(): string {
    return this._portalName;
  }

  set portalName(value: string) {
    this._portalName = value;
  }

  get domainName(): string {
    return this._domainName;
  }

  set domainName(value: string) {
    this._domainName = value;
  }

  get webPages(): Array<{ id: string; name: string }> {
    return this._webPages;
  }

  set webPages(value: Array<{ id: string; name: string }>) {
    this._webPages = value;
  }
}

export class WebPageAnalyseResult extends DomainObject {
  private _lemmatization: Map<string, string> | null;
  private _normalization: Array<string> | null;
  private _tokenize: { sentence_tokenize: Map<string, Array<string>>, word_tokenize: Map<string, Array<string>> };
  private _rawText: string | null;
  private _errorText: string | null;
  private _startAnalyse: Date | null;
  private _finishAnalyse: Date | null;
  private _phase: AnalysePhase;
  private _wordCount: { word_count: Array<Map<string, string>>, unique_word: number } | null;
  private _webPageId: string;


  constructor(id: string, creationTime: Date, lastModifiedTime: Date, ts: number, lemmatization: Map<string, string> | null, normalization: Array<string> | null, tokenize: { sentence_tokenize: Map<string, Array<string>>; word_tokenize: Map<string, Array<string>> }, rawText: string | null, errorText: string | null, startAnalyse: Date | null, finishAnalyse: Date | null, phase: AnalysePhase, wordCount: { word_count: Array<Map<string, string>>; unique_word: number } | null, webPageId: string) {
    super(id, creationTime, lastModifiedTime, ts);
    this._lemmatization = lemmatization;
    this._normalization = normalization;
    this._tokenize = tokenize;
    this._rawText = rawText;
    this._errorText = errorText;
    this._startAnalyse = startAnalyse;
    this._finishAnalyse = finishAnalyse;
    this._phase = phase;
    this._wordCount = wordCount;
    this._webPageId = webPageId;
  }

  static get tableHeaders(): Array<string> {
    return ["ID", "Lemmatization", "Normalization", "Raw Text", "Error Text", "Start Analyse", "Finish Analyse", "Creation Time", "Last Modified Time"]
  }


  get lemmatization(): Map<string, string> | null {
    return this._lemmatization;
  }

  set lemmatization(value: Map<string, string> | null) {
    this._lemmatization = value;
  }

  get normalization(): Array<string> | null {
    return this._normalization;
  }

  set normalization(value: Array<string> | null) {
    this._normalization = value;
  }

  get tokenize(): { sentence_tokenize: Map<string, Array<string>>; word_tokenize: Map<string, Array<string>> } {
    return this._tokenize;
  }

  set tokenize(value: { sentence_tokenize: Map<string, Array<string>>; word_tokenize: Map<string, Array<string>> }) {
    this._tokenize = value;
  }

  get rawText(): string | null {
    return this._rawText;
  }

  set rawText(value: string | null) {
    this._rawText = value;
  }

  get errorText(): string | null {
    return this._errorText;
  }

  set errorText(value: string | null) {
    this._errorText = value;
  }

  get startAnalyse(): Date | null {
    return this._startAnalyse;
  }

  set startAnalyse(value: Date | null) {
    this._startAnalyse = value;
  }

  get finishAnalyse(): Date | null {
    return this._finishAnalyse;
  }

  set finishAnalyse(value: Date | null) {
    this._finishAnalyse = value;
  }

  get phase(): AnalysePhase {
    return this._phase;
  }

  set phase(value: AnalysePhase) {
    this._phase = value;
  }

  get wordCount(): { word_count: Array<Map<string, string>>; unique_word: number } | null {
    return this._wordCount;
  }

  set wordCount(value: { word_count: Array<Map<string, string>>; unique_word: number } | null) {
    this._wordCount = value;
  }

  get webPageId(): string {
    return this._webPageId;
  }

  set webPageId(value: string) {
    this._webPageId = value;
  }
}

export class WebPage extends DomainObject {
  private _url: string;
  private _resourceName: string;
  private _metaData: string;
  private _head: string;
  private _pageText: string;
  private _webPortalId: string | null;
  private _webPortalName: string | null;
  private _webPageAnalyseResultId: string | null;
  private _webPageAnalyseResultName: string | null;


  constructor(id: string, creationTime: Date, lastModifiedTime: Date, ts: number, url: string, resourceName: string, metaData: string, head: string, pageText: string, webPortalId: string, webPortalName: string, webPageAnalyseResultId: string, webPageAnalyseResultName: string) {
    super(id, creationTime, lastModifiedTime, ts);
    this._url = url;
    this._resourceName = resourceName;
    this._metaData = metaData;
    this._head = head;
    this._pageText = pageText;
    this._webPortalId = webPortalId;
    this._webPortalName = webPortalName;
    this._webPageAnalyseResultId = webPageAnalyseResultId;
    this._webPageAnalyseResultName = webPageAnalyseResultName;
  }

  static get tableHeaders(): Array<string> {
    return ["ID", "URL", "Resource", "Metadata", "Head", "Page Text", "Web Portal", "Web Page Analyse Result", "Creation Time", "Last Modified Time"]
  }

  get url(): string {
    return this._url;
  }

  set url(value: string) {
    this._url = value;
  }

  get resourceName(): string {
    return this._resourceName;
  }

  set resourceName(value: string) {
    this._resourceName = value;
  }

  get metaData(): string {
    return this._metaData;
  }

  set metaData(value: string) {
    this._metaData = value;
  }

  get head(): string {
    return this._head;
  }

  set head(value: string) {
    this._head = value;
  }

  get pageText(): string {
    return this._pageText;
  }

  set pageText(value: string) {
    this._pageText = value;
  }

  get webPortalId(): string | null {
    return this._webPortalId;
  }

  set webPortalId(value: string | null) {
    this._webPortalId = value;
  }

  get webPortalName(): string | null {
    return this._webPortalName;
  }

  set webPortalName(value: string | null) {
    this._webPortalName = value;
  }

  get webPageAnalyseResultId(): string | null {
    return this._webPageAnalyseResultId;
  }

  set webPageAnalyseResultId(value: string | null) {
    this._webPageAnalyseResultId = value;
  }

  get webPageAnalyseResultName(): string | null {
    return this._webPageAnalyseResultName;
  }

  set webPageAnalyseResultName(value: string | null) {
    this._webPageAnalyseResultName = value;
  }
}
