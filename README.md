# TrainingProject_Nekonosuzu

2020年度 新卒研修 実習課題向けのアプリ「nekonosuzu」です

## このアプリ自体のコンセプト

### 解決したい課題

- 社内には、スマートフォン・SIMカード・ケーブル類など、開発に使う共用の備品がある
- キャビネットに収納されている
- OSバージョン、機種など、特定の端末を探すことがある
- 誰が使っているのか、どこにあるのかが、きちんと管理されていないこともある
- 結果、地道な捜索が始まる

### 管理アプリ「nekonosuzu」で解決する

概要: https://docs.google.com/presentation/d/1FaCVUq8AWDaebmhYfoXpYkvbUkRu1LQqr4XSC66aIJ4/edit?usp=sharing

- 端末情報や利用状況を、スマホから簡単に検索閲覧できるようにする
- QRコードの読み込みにより、端末の特定を簡素化する
- 社員証の読み込みにより、利用開始手続きを簡素化する

## 課題

### 課題1

動かし、コードを読む

- 実機にインストールし、動作させてみる
- 問題
  - アプリ起動時に最初に表示される画面は、どのクラス？
  - そこで表示されるリストの各アイテムのレイアウトは、どのXMLファイル？
  - そのリストをクリックしたときに次の画面を開くコードは、どの関数？
  - 開かれる画面は、どのクラス？

### 課題2

文言リソース

- アプリの名前が長いので変えてみる
- リスト画面、詳細画面のタイトルも変えてみる
  - 端末の言語設定により違う文言を使う

### 課題3

ListView (EquipmentListFragmentをデバッグ)

- EquipmentListFragment へ XML を読み込む箇所はどこ？
  - XMLで指定したID名で変数が参照可能となっていることを確認
- listViewとadapterとlistの関係を追う
  - データソースであるlistはどこから来ている？
  - listとlistViewはどのようにつながっている？
- listのアイテム数を5から20に増やす
  - スクロールすると発生する不具合を見つけ、修正してみる

### 課題4

機能実装: キーワード検索

- EquipmentListViewModelにあるTODO箇所を実装し、ユーザが入力したキーワードにマッチしたアイテムのみ表示されるようにする

### 課題5

機能実装: リスト画面のUI改善

- EquipmentListFragmentのレイアウトやAdapterのTODO箇所やを実装し、より使いやすい/見やすいUIに変更する
  - 表示できる/すべきデータは何か？
  - どの位置に配置する？
  - 色、大きさ、その他

### 課題6

機能実装: QRカメラによる詳細画面の起動

- QrCaptureActivityを起動する
- 認識されたコードが返ってくるので、それを受け取る
- そのコードから、リストに表示されているアイテムにIDが一致するものがあるかを探す
- 一致するアイテムがあれば、そのアイテムをリスト画面でタップしたときと同じ動作になるようにする
- 一致するアイテムがなければ「見つかりません」と表示する

### 課題7

QRコード認識の改善

- QRコード認識時に、2つ以上のコードが認識された場合、どうなるべき？

### 課題8

Firebase の Cloud Firestore に切り替える

- ドキュメント https://firebase.google.com/docs/firestore/ を参照して実装する
  - Cloud Firestore プロジェクトの作成は済み
    - google-services.json は https://drive.google.com/drive/folders/1D3KhNyykIgIRVRNiOt_9qGOGvRUn4NC3 より入手する
  - データも Firebase コンソール から追加済み
    - https://drive.google.com/drive/folders/1D3KhNyykIgIRVRNiOt_9qGOGvRUn4NC3 のスクショを参照
- FirebaseEquipment.kt を実装する
  - "equipments" コレクションから データを取得する あるいは リアルタイムアップデートを入手する、で実装する
