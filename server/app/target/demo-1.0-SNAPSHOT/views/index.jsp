<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.example.demo.utils.Misc" %>
<jsp:include page="../header.jsp" />
<table class="list">
    <thead>
    <tr class="list-item header">
        <td>Langue</td>
        <td>Titre</td>
        <td>Année édition</td>
        <td>Prix</td>
        <td>Auteur</td>
        <td>Editeur</td>
        <td class="list-item-actions no-hover">
            <button onclick="toggleCreate(event)">
                <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24" fill="#fff"><path d="M440-280h80v-160h160v-80H520v-160h-80v160H280v80h160v160Zm40 200q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z"/></svg>
            </button>
        </td>
    </tr>
    </thead>
    <tbody>

<c:forEach items="${books}" var="book">
    <tr class="list-item">
        <td>${ book.getLanguage() }</td>
        <td>${ book.getTitle() }</td>
        <td>${ book.getEditionYear() }</td>
        <td>${ Misc.floatToString(book.getPrice()) }€</td>
        <td>${ fn:substring(book.getAuthorFirstname(), 0, 1) }.${book.getAuthorLastname()}</td>
        <td>${ book.getEditor() }</td>
        <td class="list-item-actions">
            <button onclick="window.location.assign('book/${ book.getId() }');">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -960 960 960">
                    <path d="M480-320q75 0 127.5-52.5T660-500q0-75-52.5-127.5T480-680q-75 0-127.5 52.5T300-500q0 75 52.5 127.5T480-320Zm0-72q-45 0-76.5-31.5T372-500q0-45 31.5-76.5T480-608q45 0 76.5 31.5T588-500q0 45-31.5 76.5T480-392Zm0 192q-146 0-266-81.5T40-500q54-137 174-218.5T480-800q146 0 266 81.5T920-500q-54 137-174 218.5T480-200Zm0-300Zm0 220q113 0 207.5-59.5T832-500q-50-101-144.5-160.5T480-720q-113 0-207.5 59.5T128-500q50 101 144.5 160.5T480-280Z"/>
                </svg>
            </button>
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
                    '${book.getLanguage()}')">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -960 960 960">
                    <path d="M200-200h56l345-345-56-56-345 345v56Zm572-403L602-771l56-56q23-23 56.5-23t56.5 23l56 56q23 23 24 55.5T829-660l-57 57Zm-58 59L290-120H120v-170l424-424 170 170Zm-141-29-28-28 56 56-28-28Z"></path>
                </svg>
            </button>
            <form action="book" method="post">
                <input type="hidden" name="action" value="remove">
                <input type="hidden" name="id" value="${book.getId()}">
                <button type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -960 960 960"><path d="M280-120q-33 0-56.5-23.5T200-200v-520h-40v-80h200v-40h240v40h200v80h-40v520q0 33-23.5 56.5T680-120H280Zm400-600H280v520h400v-520ZM360-280h80v-360h-80v360Zm160 0h80v-360h-80v360ZM280-720v520-520Z"/></svg>
                </button>
            </form>
        </td>
    </tr>

</c:forEach>
    </tbody>
</table>
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
            language,});
        toggleModal('Modifier', template, false);
    }
    const toggleCreate = (e) => {
        e.preventDefault();
        const template = getForm('Créer', 'create');
        toggleModal('Créer', template, false);
    }
</script>
<jsp:include page="../footer.jsp" />
