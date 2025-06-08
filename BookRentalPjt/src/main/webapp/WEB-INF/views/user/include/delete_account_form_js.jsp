<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

    function deleteAccountForm() {
        console.log('deleteAccountForm() CALLED!!');

        let form = document.delete_account_form;

        if (form.u_m_id.value == '') {
            alert('INPUT USER ID.');
            form.u_m_id.focus();

        } else if (form.u_m_pw.value == '') {
            alert('INPUT USER PASSWORD.');
            form.u_m_pw.focus();

        } else {
            if (confirm('계정을 삭제하시겠습니까?')) {
                form.submit();
            }
        }

    }

</script>
