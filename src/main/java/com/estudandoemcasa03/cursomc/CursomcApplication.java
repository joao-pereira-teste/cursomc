package com.estudandoemcasa03.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estudandoemcasa03.cursomc.domain.Categoria;
import com.estudandoemcasa03.cursomc.domain.Cidade;
import com.estudandoemcasa03.cursomc.domain.Cliente;
import com.estudandoemcasa03.cursomc.domain.Endereco;
import com.estudandoemcasa03.cursomc.domain.Estado;
import com.estudandoemcasa03.cursomc.domain.Pagamento;
import com.estudandoemcasa03.cursomc.domain.PagamentoComBoleto;
import com.estudandoemcasa03.cursomc.domain.PagamentoComCartao;
import com.estudandoemcasa03.cursomc.domain.Pedido;
import com.estudandoemcasa03.cursomc.domain.Produto;
import com.estudandoemcasa03.cursomc.domain.enums.EstadoPagamento;
import com.estudandoemcasa03.cursomc.domain.enums.TipoCliente;
import com.estudandoemcasa03.cursomc.repositories.CategoriaRepository;
import com.estudandoemcasa03.cursomc.repositories.CidadeRepository;
import com.estudandoemcasa03.cursomc.repositories.ClienteRepository;
import com.estudandoemcasa03.cursomc.repositories.EnderecoRepository;
import com.estudandoemcasa03.cursomc.repositories.EstadoRepository;
import com.estudandoemcasa03.cursomc.repositories.PagamentoRepository;
import com.estudandoemcasa03.cursomc.repositories.PedidoRepository;
import com.estudandoemcasa03.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	
	@Autowired
	private CategoriaRepository categoriarepository;
    @Autowired
    private ProdutoRepository produtorepository;
    @Autowired
    private EstadoRepository estadorepository;
    @Autowired
    private CidadeRepository cidaderepository;
    @Autowired
    private ClienteRepository clienterepository;
    @Autowired
    private EnderecoRepository enderecorepository;
    @Autowired
    private PedidoRepository pedidorepository;
    @Autowired
    private PagamentoRepository pagamentorepository;
 
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null,"Infomatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriarepository.saveAll(Arrays.asList(cat1,cat2));
		produtorepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade (null,"Uberlandia",est1);
		Cidade c2 = new Cidade (null,"São Paulo",est2);
		Cidade c3 = new Cidade (null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		estadorepository.saveAll(Arrays.asList(est1,est2));
		cidaderepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria da Silva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null,"Rua flores","300","Apto 303","Jardim","38220834",cli1,c1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		clienterepository.saveAll(Arrays.asList(cli1));
		enderecorepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYY hh:mm");
		
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),
		cli1,e1	);
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"),
				cli1,e2	);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,
			ped1,6	);
		ped1.setPagamento(pagto1);
		
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,
				ped2, sdf.parse("20/10/2017 00:00"),null	);
			ped2.setPagamento(pagto2);
			
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidorepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentorepository.saveAll(Arrays.asList(pagto1,pagto2));
		
	}

}
