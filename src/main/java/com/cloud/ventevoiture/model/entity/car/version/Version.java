
package com.cloud.ventevoiture.model.entity.car.version;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author dev
 */
@Builder
@Data
@Entity
@Table(name = "version")
@AllArgsConstructor
@NoArgsConstructor
public class Version {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idVersion;
    String intitule;
    
    String details;
    
}
