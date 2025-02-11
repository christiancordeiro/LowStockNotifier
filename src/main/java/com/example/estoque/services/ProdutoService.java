package com.example.estoque.services;

import com.example.estoque.domain.Produto;
import com.example.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    JavaMailSender javaMailSender;

    private static final Logger logger = Logger.getLogger(ProdutoService.class.getName());

    @Value("${EMAIL_DESTINATARIO}")
    private String emailDestinatario;

    public Produto createdProductInDataBase(String name, int quantidadeEstoque, int limiteEstoqueBaixo){
        Produto produto = new Produto();
        produto.setName(name);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produto.setLimiteEstoqueBaixo(limiteEstoqueBaixo);
        produtoRepository.save(produto);
        alertLowStock();
        return produto;
    }

    public void alertLowStock() {
        List<Produto> estoque = checkLowStockQuantity();
        logger.info("Verificando produtos com estoque baixo...");

        if (!estoque.isEmpty()) {
            logger.info("Produtos com estoque baixo encontrados. Preparando para enviar e-mail...");

            try {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(emailDestinatario);
                msg.setSubject("Alerta: Estoque baixo");

                StringBuilder emailContent = new StringBuilder("Os seguintes produtos estão com estoque baixo:\n\n");
                for (Produto produto : estoque) {
                    emailContent.append("ID: ").append(produto.getId())
                            .append(", Nome: ").append(produto.getName())
                            .append(", Quantidade em estoque: ").append(produto.getQuantidadeEstoque())
                            .append("\n");
                }
                msg.setText(emailContent.toString());

                logger.info("Enviando e-mail de alerta...");
                javaMailSender.send(msg);
                logger.info("E-mail de alerta enviado com sucesso.");

            } catch (Exception e) {
                logger.severe("Erro ao enviar e-mail: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            logger.info("Nenhum produto com estoque baixo encontrado.");
        }
    }

    public List<Produto> checkLowStockQuantity() {
        return produtoRepository.findByQuantidadeEstoqueLessThanEqual(5);
    }
}
