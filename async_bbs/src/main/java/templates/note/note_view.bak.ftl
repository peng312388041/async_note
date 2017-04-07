<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<#assign text>
${context.result}
</#assign>
<#assign json=text?eval />
 
${context.result.id}
${json.id}
</body>
</html>