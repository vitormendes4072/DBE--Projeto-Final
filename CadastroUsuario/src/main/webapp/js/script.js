function consultaUsuario(){
	//pegando valor com jquery do form
	var cpf 		= $("#cpf").val();

	var formData = { 
		'cpf' 			: cpf
	 };
	 
	$.ajax({
		type		    : 'POST',
		url				: '/CadastroUsuario/ConsultarUsuario',
		contentType   	: 'application/json',
		data		    :  JSON.stringify(formData),
		dataType	    : 'json',
		encode			: true,
		success			: function(data) {
			alertaSuccess("Usuario : "+data.nomeUsuario);
		},
		error: function(result) {
			alertaErro("Nenhum usuario encontrado");
		}
	});
	return false;
}

function salvaUsuario(){
	//pegando valor com jquery do form
	
	var nome 		= $("#nome").val();
	var dtNasc 		= $("#dtNasc").val();
	var cpf 		= $("#cpf").val();
	var genero 		= $("#genero").val();
	var endereco 	= $("#endereco").val();
	var numero 		= $("#numero").val();
	var cidade 		= $("#cidade").val();
	var estado 		= $("#estado").val();
	var cep 		= $("#cep").val();
	var bairro 		= $("#bairro").val();
	var telefone	= $("#telefone").val();
	

	var formData = { 
		'nomeUsuario' 	: nome,
		'dataNascimento': dtNasc,
		'cpf' 			: cpf,
		'genero'		: genero,
		'logradouro'	: endereco,
		'numLogradouro'	: numero,
		'cidade'		: cidade,
		'estado'		: estado,
		'cep'			: cep,
		'bairro'		: bairro,
		'telefone'		: telefone
		
		
		
	 };
	$.ajax({
		type		    : 'POST',
		url				: '/CadastroUsuario/SalvarUsuario',
		contentType   	: 'application/json',
		data		    :  JSON.stringify(formData),
		dataType	    : 'json',
		encode			: true,
		success			: function(data) {
			alertaSuccess("Cadastrado com sucesso");
		},
		error: function(result) {
			alertaErro("Ocorreu um erro");
		}
	});
	return false;
}

function alertaErro(mensagem){
	$("#alertaErro").html(mensagem);
	$("#alertaErro").fadeIn()
		.css({bottom:-100,position:'fixed', display:'flex!important'})
		.animate({bottom:30}, 800, function() {
		$("#alertaErro").fadeOut(3000);
	})
}
function alertaSuccess(mensagem){
	console.log("alertaSucess: "+mensagem);
	$("#alertaSucess").html(mensagem);
	$("#alertaSucess").fadeIn()
		.css({bottom:-100,position:'fixed', display:'flex!important'})
		.animate({bottom:30}, 800, function() {
		$("#alertaSucess").fadeOut(3000);
	})
}


