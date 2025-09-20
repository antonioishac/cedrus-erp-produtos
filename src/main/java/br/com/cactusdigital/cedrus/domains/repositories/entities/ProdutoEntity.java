package br.com.cactusdigital.cedrus.domains.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PRODUTO")
public class ProdutoEntity {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO_UNIDADE")
    private UnidadeEntity unidade;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ID_TRIBUT_GRUPO_TRIBUTARIO")
    private GrupoTributarioEntity grupoTribitario;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO_TIPO")
    private TipoEntity tipo;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO_SUBGRUPO")
    private SubgrupoEntity subGrupo;

    @Column(name = "GTIN", length = 14)
    private String gtin;

    @Column(name = "CODIGO_INTERNO", length = 50)
    private String codigoInterno;

    @Column(name = "NOME", length = 100)
    private String nome;

    @Column(name = "DESCRICAO", length = 250)
    private String descricao;

    @Column(name = "DESCRICAO_PDV", length = 30)
    private String descricaoPdv;

    @Column(name = "VALOR_COMPRA")
    private BigDecimal valorCompra;

    @Column(name = "VALOR_VENDA")
    private BigDecimal valorVenda;

    @Column(name = "VALOR_CUSTO")
    private BigDecimal valorCusto;

    @Column(name = "QUANTIDADE_ESTOQUE")
    private BigDecimal quantidadeEstoque;

    @Column(name = "ESTOQUE_MINIMO")
    private BigDecimal estoqueMinimo;

    @Column(name = "ESTOQUE_MAXIMO")
    private BigDecimal estoqueMaximo;

    @Column(name = "CODIGO_NCM", length = 8)
    private String codigoNcm;

    @Column(name = "IAT", length = 1)
    private String iat;

    @Column(name = "IPPT", length = 1)
    private String ippt;

    @Column(name = "TIPO_ITEM_SPED", length = 2)
    private String tipoItemSped;

    @Column(name = "TAXA_IPI")
    private BigDecimal taxaIpi;

    @Column(name = "TAXA_ISSQN")
    private String taxaIssqn;

    @Column(name = "TAXA_PIS")
    private BigDecimal taxaPis;

    @Column(name = "TAXA_COFINS")
    private BigDecimal taxaCofins;

    @Column(name = "TAXA_ICMS")
    private BigDecimal taxaIcms;

    @Column(name = "CST", length = 3)
    private String cst;

    @Column(name = "CSOSN", length = 4)
    private String csosn;

    @Column(name = "TOTALIZADOR_PARCIAL", length = 10)
    private String totalizadorParcial;

    @Column(name = "CODIGO_BALANCA")
    private Integer codigoBalanca;

    @Column(name = "PAF_P_ST", length = 1)
    private String PafPst;

    @Column(name = "HASH_REGISTRO", length = 32)
    private String hashRegistro;

    @Column(name = "SITUACAO", length = 1)
    private String situacao;

    @Column(name = "CODIGO_CEST", length = 7)
    private String codigoCest;
}
