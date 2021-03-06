package cat.xtec.merli.domain.voc;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import cat.xtec.merli.domain.EnumSource;
import cat.xtec.merli.domain.EnumString;
import cat.xtec.merli.bind.*;


/**
 * Underlying organizational structure of a learning object (LOMv1.0).
 */
@XmlEnum
@XmlType(name = "structure")
public enum Structure implements EnumString<Structure> {

    /** Indivisible resource */
    @XmlEnumValue("atomic")
    ATOMIC("atomic"),

    /** Set of unlinked resources */
    @XmlEnumValue("collection")
    COLLECTION("collection"),

    /** Set of resources linked as a tree */
    @XmlEnumValue("hierarchical")
    HIERARCHICAL("hierarchical"),

    /** Set of resources linked as a path */
    @XmlEnumValue("linear")
    LINEAR("linear"),

    /** Set of resources linked as a graph */
    @XmlEnumValue("networked")
    NETWORKED("networked");

    /** Source of the vocabulary */
    private final EnumSource source = EnumSource.LOM;

    /** Enumeration value */
    private final String value;


    /**
     * Enumeration constructor
     *
     * @param value
     */
    Structure(String value) {
        this.value = value;
    }


    /**
     * {@inheritDoc}
     */
    public EnumSource source() {
        return source;
    }


    /**
     * {@inheritDoc}
     */
    @DucString
    public String value() {
        return value;
    }


    /**
     * {@inheritDoc}
     */
    @DucCreator()
    public static Structure fromValue(String value) {
        return EnumString.from(Structure.class, value);
    }

}
