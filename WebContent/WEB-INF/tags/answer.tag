<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="answerContent" fragment="true" %>
<%@attribute name="answerID" fragment="true" %>
<tr>
    <td>
        <label class="checkbox">
            <input type="checkbox" name="answers" value="<jsp:invoke fragment="answerID"/>" data-toggle="checkbox">
        </label>
    </td>
    <td><jsp:invoke fragment="answerContent"/></td>
</tr>