<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
	<link rel="stylesheet" type="text/css" href="../css/Styles.css">
	<title>Edit Message</title>
</head>
<body>
	<div class="center">
		<div class="header">${user.name} Edit Message</div>
		<form action="/editMessage/${messages.id}" method="post">
			<input type="hidden" name="userName" value="${user.name}">
			<@spring.formTextarea "messages.message" "rows='4' cols='40'" /><br />
			<@spring.showErrors "<br />" "color: red;" /> <br />
			<a href="/messages" class="btn btn-primary">Back</a>
			<button type="submit" class="btn btn-primary">Edit Message</button>
		</form>
	</div>
</body>
</html>