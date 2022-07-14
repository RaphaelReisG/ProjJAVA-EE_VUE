<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ page import="model.Conteiner" %>
<%@ page import="model.Movimentacao" %>

<%@ page import="java.util.ArrayList" %>

<%
	ArrayList<Conteiner> listaConteiner = (ArrayList<Conteiner>) request.getAttribute("conteiners");
	ArrayList<Movimentacao> listaMov = (ArrayList<Movimentacao>) request.getAttribute("movimentacoes");

%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>HOME</title>
<link rel="stylesheet" type="text/css" href="estilo1.css">
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

</head>
<body>
	<div id="app1">
		<h1>{{obj1}} e {{obj2}}</h1>
	
		<button class="botao" v-on:click="addConteiner">+ add Conteiner</button>
		<button class="botao" v-on:click="addMov">+ add Movimentação</button>
		
		<div class="conteudo">
			<h2>{{obj1}}</h2>
			
			<form action="updateConteiner">
				<table>
					<tr>
						<th v-for="dado in topoConteiner">{{dado}}</th>
					</tr>
					<tbody v-for="(dado, index) in conteiner">
						<tr v-if="dado.edicao == false">
							<td>{{dado.numero}}</td>
							<td>{{dado.cliente}}</td>
							<td>{{dado.tipo}}</td>
							<td>{{dado.status}}</td>
							<td>{{dado.categoria}}</td>
							<td>
								<button class="botao" v-on:click="editConteiner(index)">Editar</button>
								<button class="botao" v-on:click="removerConteiner(dado.numero)">Excluir</button>
							</td>
						</tr>
						<tr v-else>
							<td><input type="text" name="numero" readonly  placeholder="ABCD1234567" v-model="dado.numero"></td>
							<td><input type="text" name="cliente" pattern="[A-z ]*" v-model="dado.cliente" placeholder="Nome do cliente" required></td>
							<td>
								<select name="tipo" v-model="dado.tipo">
									<option value="10">10</option>
									<option value="20">20</option>
								</select>
							</td>
							<td>	
								<select name="estado" v-model="dado.status">
									<option value="Cheio">Cheio</option>
									<option value="Vazio">Vazio</option>
								</select>
							
							</td>
							<td>
								<select name="categoria" v-model="dado.categoria">
									<option value="Importação">Importação</option>
									<option value="Exportação">Exportação</option>
								</select>
							</td>
							<td>
								<input class="botao" type="submit" value="Salvar">
								
							</td>
						</tr>
					</tbody>
					
				</table>
			
			</form>
		</div>
		
		
		<div class="conteudo">
			<h2>{{obj2}}</h2>
			
			<form action="updateMov">
				<table>
					<tr>
						<th v-for="dado in topoMov">{{dado}}</th>
					</tr>
					<tbody v-for="(dado, index) in movimentacao">
						<tr v-if="dado.edicao == false">
							<td>{{dado.id}}</td>
							<td>{{dado.numero}}</td>
							<td>{{dado.tipoMov}}</td>
							<td>{{dado.inicio}}</td>
							<td>{{dado.fim}}</td>
							<td>
								<button class="botao" v-on:click="editMov(index)">Editar</button>
								<button class="botao" v-on:click="removerMov(dado.id)">Excluir</button>
							</td>
						</tr>
						<tr v-else>
							<td><input type="text" name="id" readonly v-model="dado.id"></td>
							<td>
								<select  name="numero" v-model="dado.numero" >
									<option v-for="cont in conteiner" >{{cont.numero}}</option>
								</select>
							</td>
							<td>
								<select name="tipoMov" v-model="dado.tipoMov">
									<option value="Embarque">Embarque</option>
									<option value="Descarga">Descarga</option>
									<option value="Gate In">Gate In</option>
									<option value="Gate Out">Gate Out</option>
									<option value="Reposicionamento">Reposicionamento</option>
									<option value="Pesagem">Pesagem</option>
									<option value="Scanner">Scanner</option>
								</select>
							</td>
							<td>	
								<input type="datetime-local" name="inicio" v-model="dado.inicio" >
							
							</td>
							<td>
								<input type="datetime-local" name="fim" v-model="dado.fim" >
							</td>
							<td>
								<input class="botao" type="submit" value="Salvar">
								
							</td>
						</tr>
					</tbody>
					
				</table>
			
			</form>
		</div>
		
		
		
		
		
		
		<div class="addNovo" v-if="novoConteiner == true">
			<h1>Novo Conteiner</h1>
			<button class="fechar" v-on:click="cancelar">X</button>
			<form action="inserirConteiner">
				<label> Nº Conteiner: </label>
				<input type="text" name="numero" pattern="^([A-Z]{4}[0-9]{7})$" placeholder="ABCD1234567" required>
				<br><br>
				<label> Cliente: </label>
				<input type="text" name="cliente" pattern="[A-z ]*" placeholder="Nome do cliente" required>
				<br><br>
				<label> Tipo: </label>
				<select name="tipo">
					<option value="10">10</option>
					<option value="20">20</option>
				</select>
				<br><br>
				<label> Status: </label>
				<select name="estado">
					<option value="Cheio">Cheio</option>
					<option value="Vazio">Vazio</option>
				</select>
				<br><br>
				<label> Categoria: </label>
				<select name="categoria">
					<option value="Importação">Importação</option>
					<option value="Exportação">Exportação</option>
				</select>
				<br><br>
				<input class="botao" type="submit" value="Criar">
				
			</form>
		</div>
		
		<div class="addNovo" v-if="novoMov == true">
			<h1>Novo Movimentação</h1>
			<button class="fechar" v-on:click="cancelar">X</button>
			<form action="inserirMov">
				<label> Nº Conteiner: </label>
				<select  name="numero" >
					<option v-for="cont in conteiner" >{{cont.numero}}</option>
				</select>
				<br><br>
				<label> Tipo: </label>
				<select name="tipoMov">
					<option value="Embarque">Embarque</option>
					<option value="Descarga">Descarga</option>
					<option value="Gate In">Gate In</option>
					<option value="Gate Out">Gate Out</option>
					<option value="Reposicionamento">Reposicionamento</option>
					<option value="Pesagem">Pesagem</option>
					<option value="Scanner">Scanner</option>
				</select>
				<br><br>
				<label>Inicio: </label>
				<input type="datetime-local" name="inicio" >
				<br><br>
				<label> Fim: </label>
				<input type="datetime-local" name="fim" >
				<br><br>
				<input class="botao" type="submit" value="Criar">
			</form>
		</div>
	
	</div>

</body>

<script>
	var app1 = new Vue({
		el: '#app1',
		data: {
			obj1: 'Conteiners',
			obj2: 'Movimentações',
			novoConteiner: false,
			novoMov: false,
			
			topoConteiner: ["Numero", "Cliente", "Tipo", "Status", "Categoria", "Opções"],
			topoMov: ["ID", "Numero", "Tipo", "Inicio", "Fim", "Opções"],
			conteiner: [
				<% for(int i=0; i<listaConteiner.size(); i++) {%>
					{
						edicao: false,
						numero: "<%=listaConteiner.get(i).getNumero()%>",
						cliente: "<%=listaConteiner.get(i).getCliente()%>",
						tipo: "<%=listaConteiner.get(i).getTipo()%>",
						status: "<%=listaConteiner.get(i).getEstado()%>",
						categoria: "<%=listaConteiner.get(i).getCategoria()%>"},
						
					
				
				<% } %>
			],
			movimentacao: [
				<% for(int i=0; i<listaMov.size(); i++) {%>
					{
						edicao: false,
						id: "<%=listaMov.get(i).getId()%>",
						numero: "<%=listaMov.get(i).getNumero()%>",
						tipoMov: "<%=listaMov.get(i).getTipoMov()%>",
						inicio: "<%=listaMov.get(i).getInicio()%>",
						fim: "<%=listaMov.get(i).getFim()%>"},
						
					
				
				<% } %>
			],
			
			
		},
		methods: {
			addConteiner: function(){
				this.novoMov = false;
				this.novoConteiner = true;
			},
			
			addMov: function(){
				this.novoConteiner = false;
				this.novoMov = true;
			},
			
			editConteiner: function(index){
				this.conteiner[index]['edicao'] = true;
			},
			
			editMov: function(index){
				this.movimentacao[index]['edicao'] = true;
			},
			
			removerConteiner: function(numero){
				let resposta = confirm("Tem cereza que quer excluir o conteiner?");
				if(resposta == true){
					window.location.href = "deleteConteiner?numero="+numero;
					alert("Excluido com sucesso.");
				}
			},
			
			removerMov: function(id){
				let resposta = confirm("Tem cereza que quer excluir a movimentacao?");
				if(resposta == true){
					window.location.href = "deleteMov?id="+id;
					alert("Excluido com sucesso.");
				}
			},
		
			cancelar: function(){
				this.novoConteiner = false;
				this.novoMov= false;
			}
		}
	})


</script>

</html>