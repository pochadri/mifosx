/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.loanaccount.service;

import java.util.HashSet;
import java.util.Set;

import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.mifosplatform.portfolio.charge.exception.LoanCollateralNotFoundException;
import org.mifosplatform.portfolio.loanaccount.domain.LoanCollateral;
import org.mifosplatform.portfolio.loanaccount.domain.LoanCollateralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class LoanCollateralAssembler {

    private final FromJsonHelper fromApiJsonHelper;
    private final CodeValueRepositoryWrapper codeValueRepository;
    private final LoanCollateralRepository loanCollateralRepository;

    @Autowired
    public LoanCollateralAssembler(final FromJsonHelper fromApiJsonHelper, final CodeValueRepositoryWrapper codeValueRepository,
            final LoanCollateralRepository loanCollateralRepository) {
        this.fromApiJsonHelper = fromApiJsonHelper;
        this.codeValueRepository = codeValueRepository;
        this.loanCollateralRepository = loanCollateralRepository;
    }

    public Set<LoanCollateral> fromParsedJson(final JsonElement element) {

        final Set<LoanCollateral> collateralItems = new HashSet<LoanCollateral>();

        if (element.isJsonObject()) {
            final JsonObject topLevelJsonElement = element.getAsJsonObject();

            if (topLevelJsonElement.has("collateral") && topLevelJsonElement.get("collateral").isJsonArray()) {
                final JsonArray array = topLevelJsonElement.get("collateral").getAsJsonArray();
                for (int i = 0; i < array.size(); i++) {

                    final JsonObject collateralItemElement = array.get(i).getAsJsonObject();

                    final Long id = fromApiJsonHelper.extractLongNamed("id", collateralItemElement);
                    final Long collateralTypeId = fromApiJsonHelper.extractLongNamed("type", collateralItemElement);
                    final CodeValue collateralType = this.codeValueRepository.findOneWithNotFoundDetection(collateralTypeId);
                    final String description = fromApiJsonHelper.extractStringNamed("description", collateralItemElement);

                    if (id == null) {
                        collateralItems.add(LoanCollateral.from(collateralType, description));
                    } else {
                        LoanCollateral loanCollateralItem = this.loanCollateralRepository.findOne(id);
                        if (loanCollateralItem == null) { throw new LoanCollateralNotFoundException(id); }

                        loanCollateralItem.assembleFrom(collateralType, description);

                        collateralItems.add(loanCollateralItem);
                    }
                }
            } else {
                // no charges passed, use existing ones against loan
            }

        }

        return collateralItems;
    }
}