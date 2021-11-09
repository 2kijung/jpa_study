/**
 * 
 */
 
 let urlEncoder = paramObj =>{
	
	let arr = [];
	
	for(key in paramObj){
		let param = key + '=' + encodeURIComponent(paramObj[key]);
		arr.push(param);
	}
	
	return arr.join('&');
}