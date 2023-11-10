<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.example.demo.utils.Misc" %>
<jsp:include page="../header.jsp" />
<div class="book-detail">
    <div class="book-header">
        <button class="primary_button" onclick="window.location.assign('/demo_war/book')">
            Retour
        </button>
        <h2 style="flex:1;text-align: center;"><c:out value="${book.getTitle()}" /></h2>
        <form action="/book/delete/<c:out value="${book.getId()}" />" method="post">
            <button type="submit" class="primary_button">
                Supprimer
            </button>
        </form>
    </div>
    <div class="book-info">
        <div class="info-item">
            <strong>Langue:</strong> <c:out value="${book.getLanguage()}" />
        </div>
        <div class="info-item">
            <strong>ISBN:</strong> ${ book.getISBN() }
        </div>
        <div class="info-item">
            <strong>Thème:</strong> ${ book.getTheme() }
        </div>
        <div class="info-item">
            <strong>Nombre de pages:</strong> ${ book.getPageNbr() }
        </div>
        <div class="info-item">
            <strong>Format:</strong> ${ book.getFormat() }
        </div>
        <div class="info-item">
            <strong>Auteur:</strong> ${book.getAuthorFirstname()} ${book.getAuthorLastname()}
        </div>
        <div class="info-item">
            <strong>Editeur:</strong> ${ book.getEditor() }
        </div>
        <div class="info-item">
            <strong>Année d'édition:</strong> ${ book.getEditionYear() }
        </div>
        <div class="info-item">
            <strong>Prix:</strong> ${ Misc.floatToString(book.getPrice()) }€
        </div>
    </div>

    <div class="actions">
        <button onclick="toggleEdit(event,
        ${book.getId()},
                '${book.getTitle()}',
                '${book.getISBN()}',
                '${book.getTheme()}',
                '${book.getPageNbr()}',
                '${book.getFormat()}',
                '${book.getAuthorFirstname()}',
                '${book.getAuthorLastname()}',
                '${book.getEditor()}',
                '${book.getEditionYear()}',
                '${book.getPrice()}',
                '${book.getLanguage()}')" class="primary_button">
            Modifier
        </button>
    </div>
</div>
<script type="text/javascript">
    <jsp:include page="form.jsp" />
    const toggleEdit = (e,
                        id,
                        title,
                        ISBN,
                        theme,
                        page_nbr,
                        format,
                        author_firstname,
                        author_lastname,
                        editor,
                        edition_year,
                        price,
                        language) => {
        e.preventDefault();
        const template = getForm('Modifier', 'edit', {
            id,
            title,
            ISBN,
            theme,
            page_nbr,
            format,
            author_firstname,
            author_lastname,
            editor,
            edition_year,
            price,
            language});
        toggleModal('Modifier', template, false);
    }
</script>
<jsp:include page="../footer.jsp" />
