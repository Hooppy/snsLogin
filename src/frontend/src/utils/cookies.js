function saveLoginStateToCookie(value) {
	document.cookie = `loginState=${value}`;
}

function saveUsernameToCookie(value) {
	document.cookie = `username=${value}`;
}

function getLoginStateFromCookie() {
	return document.cookie.replace(
		/(?:(?:^|.*;\s*)loginState\s*=\s*([^;]*).*$)|^.*$/,
		'$1',
	);
}

function getUsernameFromCookie() {
	return document.cookie.replace(
		/(?:(?:^|.*;\s*)username\s*=\s*([^;]*).*$)|^.*$/,
		'$1',
	);
}

function deleteCookie(value) {
	document.cookie = `${value}=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
}

export {
	saveLoginStateToCookie,
	saveUsernameToCookie,
	getLoginStateFromCookie,
	getUsernameFromCookie,
	deleteCookie,
};
