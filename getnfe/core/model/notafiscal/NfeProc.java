package com.br.compol.getnfe.core.model.notafiscal;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@XmlRootElement(name = "nfeProc", namespace = "http://www.portalfiscal.inf.br/nfe")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"nfe", "protocolo"})
@Getter
public class NfeProc {

    @XmlElement(name = "NFe", namespace = "http://www.portalfiscal.inf.br/nfe")
    private NFe nfe;

    @XmlElement(name = "protNFe", namespace = "http://www.portalfiscal.inf.br/nfe")
    private Protocolo protocolo;

    // ===== CLASSES INTERNAS =====

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"infNFe"})
    public static class NFe {
        @XmlElement(name = "infNFe", namespace = "http://www.portalfiscal.inf.br/nfe")
        private InformacoesNFe infNFe;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"identificacao", "emitente", "destinatario", "produtos", "total"})
    public static class InformacoesNFe {

        @XmlElement(name = "ide", namespace = "http://www.portalfiscal.inf.br/nfe")
        private Identificacao identificacao;

        @XmlElement(name = "emit", namespace = "http://www.portalfiscal.inf.br/nfe")
        private Emitente emitente;

        @XmlElement(name = "dest", namespace = "http://www.portalfiscal.inf.br/nfe")
        private Destinatario destinatario;

        @XmlElement(name = "det", namespace = "http://www.portalfiscal.inf.br/nfe")
        private List<Produto> produtos;

        @XmlElement(name = "total", namespace = "http://www.portalfiscal.inf.br/nfe")
        private Total total;

        @XmlAttribute(name = "versao")
        private String versao;

        @XmlAttribute(name = "Id")
        private String id;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    public static class Identificacao {
        @XmlElement(name = "nNF", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String numero;

        @XmlElement(name = "serie", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String serie;

        @XmlElement(name = "dhEmi", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String dataEmissao;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    public static class Emitente {
        @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String cnpj;

        @XmlElement(name = "xNome", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String razaoSocial;

        @XmlElement(name = "IE", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String inscricaoEstadual;

        @XmlElement(name = "enderEmit", namespace = "http://www.portalfiscal.inf.br/nfe")
        private Endereco endereco;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Destinatario {
        @XmlElement(name = "CPF", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String cpf;

        @XmlElement(name = "xNome", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String nome;

        @XmlElement(name = "enderDest", namespace = "http://www.portalfiscal.inf.br/nfe")
        private Endereco endereco;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Endereco {
        @XmlElement(name = "xLgr", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String logradouro;

        @XmlElement(name = "nro", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String numero;

        @XmlElement(name = "xBairro", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String bairro;

        @XmlElement(name = "xMun", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String municipio;

        @XmlElement(name = "UF", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String uf;

        @XmlElement(name = "CEP", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String cep;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Produto {
        @XmlAttribute(name = "nItem")
        private String numeroItem;

        @XmlElement(name = "prod", namespace = "http://www.portalfiscal.inf.br/nfe")
        private DetalheProduto detalhe;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DetalheProduto {
        @XmlElement(name = "cProd", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String codigo;

        @XmlElement(name = "xProd", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String descricao;

        @XmlElement(name = "vProd", namespace = "http://www.portalfiscal.inf.br/nfe")
        private BigDecimal valor;

        @XmlElement(name = "qCom", namespace = "http://www.portalfiscal.inf.br/nfe")
        private BigDecimal quantidade;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Total {
        @XmlElement(name = "ICMSTot", namespace = "http://www.portalfiscal.inf.br/nfe")
        private Valores valores;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Valores {
        @XmlElement(name = "vNF", namespace = "http://www.portalfiscal.inf.br/nfe")
        private BigDecimal valorNota;

        @XmlElement(name = "vProd", namespace = "http://www.portalfiscal.inf.br/nfe")
        private BigDecimal valorProdutos;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Protocolo {
        @XmlElement(name = "infProt", namespace = "http://www.portalfiscal.inf.br/nfe")
        private InfoProtocolo info;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class InfoProtocolo {
        @XmlElement(name = "nProt", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String numero;

        @XmlElement(name = "chNFe", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String chave;

        @XmlElement(name = "xMotivo", namespace = "http://www.portalfiscal.inf.br/nfe")
        private String status;
    }

    // ===== GETTERS EXTRAS COMUNS PARA FACILITAR O USO =====

    public Identificacao getIdentificacao() {
        return this.nfe.infNFe.identificacao;
    }

    public Emitente getEmitente() {
        return this.nfe.infNFe.emitente;
    }

    public String getDataEmissao() {
        return this.nfe.infNFe.identificacao.dataEmissao;
    }

    public String getSerie() {
        return this.nfe.infNFe.identificacao.serie;
    }

    public String getNumeroNota() {
        return this.nfe.infNFe.identificacao.numero;
    }

    public String getNomeEmitente() {
        return this.nfe.infNFe.emitente.razaoSocial;
    }

    public String getCnpjEmitente() {
        return this.nfe.infNFe.emitente.cnpj;
    }

    public String getNomeDestinatario() {
        return this.nfe.infNFe.destinatario.nome;
    }

    public String getCpfDestinatario() {
        return this.nfe.infNFe.destinatario.cpf;
    }

    public List<Produto> getProdutos() {
        return this.nfe.infNFe.produtos;
    }

    public BigDecimal getValorTotalNota() {
        return this.nfe.infNFe.total.valores.valorNota;
    }

    public BigDecimal getValorTotalProdutos() {
        return this.nfe.infNFe.total.valores.valorProdutos;
    }

    public String getNumeroProtocolo() {
        return this.protocolo.info.numero;
    }

    public String getChaveNfe() {
        return this.protocolo.info.chave;
    }

    public String getStatusAutorizacao() {
        return this.protocolo.info.status;
    }
}
