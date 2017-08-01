<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="answerContent" fragment="true" %>
<tr>
    <td>
        <label class="checkbox">
            <input type="checkbox" value="" data-toggle="checkbox">
        </label>
    </td>
    <td><jsp:invoke fragment="answerContent"/></td>
</tr>