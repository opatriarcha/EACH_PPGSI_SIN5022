### Atividade 8: ESG de aplicativo - Mandrasoft Maga Reader

O aplicativo é destinado à busca de mangas ( historias em quadrinhos predominantemente japonesas) em concentradores "não oficiais" de conteúdo na internet. Tal conteudo está organizado por publicação, capítulos e páginas, sendo a ultima uma coleção de imagens. O aplicativo busca essas meta informações e faz o download das imagens organizado por publicação/capítulo e permite a leitura dos mesmos via um leitor de imagens.

O ESG abaixo está consideranto a aplicação de busca de conteúdo, mas não contempla o leitor de imagens por questões de tamanho e escopo.

### ESG do aplicativo:

## Grafo
<img src='https://g.gravizo.com/svg?
 digraph G {
  "[" -> "Library Screen";
  "Library Screen" -> "]";
  "Library Screen" -> "Press Filter Widget";
  "Press Filter Widget" -> "Filter Detail";
  "Filter Detail" -> "Library Screen";
  "Library Screen" -> "Discord Notification Widget";
  "Library Screen" -> "General Settings Button";
  "General Settings Button" -> "General Settings Screen";
  "General Settings Screen" -> "Press Back Button1";
  "Press Back Button1" -> "Library Screen";
  "Library Screen" -> "Press Add Button";
  "Press Add Button" -> "Show Top Mangas Screen";
  "Show Top Mangas Screen" -> "Press Language Button";
  "Press Language Button" -> "Press Language Combo";
  "Press Language Combo" -> "Show Top Mangas Screen";
  "Press Title" -> "Manga Detail Widget";
  "Manga Detail Widget" -> "Press Add Button";
  "Press Add Button" -> "Show Top Mangas Screen";
  "Show Top Mangas Screen" -> "Press Search Button";
  "Press Search Button" -> "Search Screen";
  "Search Screen" -> "Insert Search Text";
  "Insert Search Text" -> "Press Inner Search Button";
  "Press Inner Search Button" -> "Show Search Result Widget";
  "Show Search Result Widget" -> "Press Result Title";
  "Press Result Title" -> "Show Search Result Widget";
  "Show Search Result Widget" -> "Press Back1 Button";
  "Press Back1 Button" -> "Show Top Mangas Screen";
  "Show Top Mangas Screen" -> "Press Heart Button";
  "Press Heart Button" -> "Show Recomendations Screen";
  "Show Recomendations Screen"; -> "Press Recomended Title";
  "Press Recomended Title" -> "Recommended Title Widget";
  "Recommended Title Widget" -> "Press Favorite Button1";
  "Press Favorite Button1" -> "Show Recomendations Screen";
  "Press Favorite Button1" -> "Back Button2";
  "Back Button2" -> "Show Top Mangas Screen";
  "Show Top Mangas Screen" -> "Press Back Button2";
  "Press Back Button2" -> "Library Screen";
  "Library Screen" -> "Press Back Button3";
  "Press Back Button3" -> "Exits Application";
  "Library Screen" -> "Press Title" -> "Show Manga Detail Screen";
  "Show Manga Detail Screen" -> "Press Read Online" -> "Show Reader Widget";
  "Show Manga Detail Screen" -> "Press Continue Offline" -> "Show Reader Widget";
  "Show Manga Detail Screen" -> "Press Web Comig Check" -> "Show Manga Details Screen";
  "Show Manga Detail Screen" -> "Press Chapter Menu" -> "Show Chapters Screen";
  "Show Manga Detail Screen" -> "Press Favourite Button" -> "Show Manga Details Screen";
  "Show Manga Detail Screen" -> "Press Share Button" -> "Choose Target" -> "Show Manga Details Screen";
  "Show Chapters Screen" -> "Press Order A-Z Button";
  "Press Play Button" -> "Show Reader Widget";
 }
'/>

<!-- "Press Order A-Z Button" -> "Show Chapters Screen";
"Show Chapters Screen" -> "Press Play Button";
"Press Download Button" -> "Show Chapters Screen";
"Show Chapters Screen" -> "Press Readed Button";
"Press Readed Button" -> "Show Chapters Screen";
"Show Chapters Screen" -> "Press Download Button";
"Show Chapters Screen" -> "Press Download All";
"Press Download All" -> "Show Chapters Screen";
"Show Chapters Screen" -> "Press Select All" -> "Show Chapters Screen"; -->
