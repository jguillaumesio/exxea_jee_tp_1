const getForm = (submit, type, book = null) => {
return '<form method="post" action="book" onsubmit="return validateForm(event)">' +
'      <input type="hidden" name="action" value="' + type + '">' +
(book !== null ? '<input type="hidden" name="id" value="' + book.id + '">' : '') +
'  <div class="input_group">' +
    '      <div style="flex:2">' +
        '            <label for="title">Titre du livre:</label>' +
        '            <input type="text" name="title" id="title" value="' + (book ? book.title : '') + '" required>' +
        '            <span class="label_error"></span>' +
        '          </div>' +
    '      <div style="flex:1">' +
        '            <label for="language">Langage:</label>' +
        '            <input type="text" name="language" id="language" value="' + (book ? book.language : '') + '" required>' +
        '            <span class="label_error"></span>' +
        '          </div>' +
    '      <div style="flex:1">' +
        '            <label for="page_nbr">Nombre de pages:</label>' +
        '            <input type="number" name="page_nbr" id="page_nbr" value="' + (book ? book.page_nbr : '') + '" required>' +
        '            <span class="label_error"></span>' +
        '          </div>' +
    '      <div style="flex:1">' +
        '            <label for="price">Prix:</label>' +
        '            <input type="text" name="price" id="price" value="' + (book ? book.price : '') + '" required>' +
        '            <span class="label_error"></span>' +
        '          </div>' +
    '    </div>' +
'  <div class="input_group">' +
    '      <div style="flex:1;">' +
        '            <label for="ISBN">ISBN:</label>' +
        '            <input type="text" name="ISBN" id="ISBN" value="' + (book ? book.ISBN : '') + '" required>' +
        '            <span class="label_error"></span>' +
        '          </div>' +
    '      <div style="flex:1;">' +
        '            <label for="theme">Thème:</label>' +
        '            <input type="text" name="theme" id="theme" value="' + (book ? book.theme : '') + '" required>' +
        '            <span class="label_error"></span>' +
        '          </div>' +
    '      <div style="flex:1;">' +
        '            <label for="format">Format:</label>' +
        '            <input type="text" name="format" id="format" value="' + (book ? book.format : '') + '" required>' +
        '            <span class="label_error"></span>' +
        '          </div>' +
    '    </div>' +
'  <div class="input_group">' +
    '      <div style="flex:1;">' +
        '            <label for="author_firstname">Prénom de l\'auteur:</label>' +
        '            <input type="text" name="author_firstname" id="author_firstname" value="' + (book ? book.author_firstname : '') + '" required>' +
        '            <span class="label_error"></span>' +
        '          </div>' +
    '      <div style="flex:1;">' +
        '            <label for="author_lastname">Nom de l\'auteur:</label>' +
        '            <input type="text" name="author_lastname" id="author_lastname" value="' + (book ? book.author_lastname : '') + '" required>' +
        '            <span class="label_error"></span>' +
        '          </div>' +
    '    </div>' +
'  <div class="input_group">' +
    '      <div style="flex:1;">' +
        '            <label for="editor">Editeur:</label>' +
        '            <input type="text" name="editor" id="editor" value="' + (book ? book.editor : '') + '" required>' +
        '            <span class="label_error"></span>' +
        '          </div>' +
    '      <div style="flex:1;">' +
        '            <label for="edition_year">Année d\'édition:</label>' +
        '            <input type="number" name="edition_year" id="edition_year" value="' + (book ? book.edition_year : '') + '" required>' +
        '            <span class="label_error"></span>' +
        '          </div>' +
    '    </div>' +
'  <div>' +
    '      <button type="submit" class="primary_button">' + submit + '</button>' +
    '    </div>' +
'</form>';
};
