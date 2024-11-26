<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link rel="stylesheet" type="text/css" href="css/Styles.css">
<title>Login Success</title>
</head>
<body>
	<div>
		<div class="center">Welcome ${user.name}</div>
		<br />

		<div class="center">
			<a href="/addMessage" class="btn btn-primary">Add Message</a> <a
				href="/logout" class="btn btn-primary">Logout</a>
		</div>
		<br />
		<div class="center">
			<table>
				<#list messages>
				<div class="divTable">
					<div class="divTableHeading">
						<div class="divTableRow">
							<div class="divTableHead">Name</div>
							<div class="divTableHead">Message</div>
							<div class="divTableHead">Date</div>
							<div class="divTableHead">Edit</div>
							<div class="divTableHead">Delete</div>
						</div>
					</div>
					<div class="divTableBody">
						<#items as message>
						<div class="divTableRow">
							<div class="divTableCell">${message.userName}</div>
							<div class="divTableCell">${message.message}</div>
							<div class="divTableCell">${message.date.format('dd MMMM yyyy')}</div>
							<div class="divTableCell">
								<a href="/editMessage/${message.id}" class="btn btn-primary">Edit</a>
							</div>
							<div class="divTableCell">
								<a href="/deleteMessage/${message.id}" class="btn btn-primary">Delete</a>
							</div>
						</div>

						</#items>
					</div>
				</div>
				<#else>
				<p>No Message</p></#list>
				</div>
				</div>
</body>
</html>