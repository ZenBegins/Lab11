<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
	<link rel="stylesheet" type="text/css" href="css/Styles.css">
	<title>Login</title>
</head>
<body>
	<form action="/Login" method="post">
		<div class="center header">Login</div>
		<br />
		<div class="divTable">
			<div class="divTableBody">
				<div class="divTableRow">
					<div class="divTableCell">
						<label for="email">Email:</label>
					</div>
					<div class="divTableCell">
						<@spring.formInput "users.email" />
						<@spring.showErrors "<br /><br />" "color: red;" /><br />
					</div>
				</div>
				<div class="divTableRow">
					<div class="divTableCell">
						<label for="password">Password:</label>
					</div>
					<div class="divTableCell">
						<@spring.formPasswordInput "users.password" />
						<@spring.showErrors "<br />" "color: red;" /><br />
					</div>
				</div>
			</div>
		</div>
		<#if errorMessage?exists>
			<div class="errorMessage">${errorMessage}</div>
		</#if>
		<br />
		<div class="center">
			<button type="submit" class="btn btn-primary">Login</button>
			<a href="/register" class="btn btn-primary">Register</a>
		</div>
	</form>
</body>
</html>