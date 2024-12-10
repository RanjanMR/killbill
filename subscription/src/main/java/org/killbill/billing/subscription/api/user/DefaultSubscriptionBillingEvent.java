/*
 * Copyright 2014-2019 Groupon, Inc
 * Copyright 2014-2019 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.subscription.api.user;

import java.util.Date;
import java.util.Objects;

import org.joda.time.DateTime;
import org.killbill.billing.catalog.api.DiscountStack;
import org.killbill.billing.catalog.api.Plan;
import org.killbill.billing.catalog.api.PlanPhase;
import org.killbill.billing.invoice.api.Discount;
import org.killbill.billing.subscription.api.SubscriptionBaseTransitionType;

public class DefaultSubscriptionBillingEvent implements SubscriptionBillingEvent {

    private final SubscriptionBaseTransitionType type;
    private final Plan plan;
    private final PlanPhase planPhase;
    private final DateTime effectiveDate;
    private final Long totalOrdering;
    private final Integer bcdLocal;

    private final Integer quantity;
    private final DateTime catalogEffectiveDate;

    private  final DiscountStack[] discountStacks;
    private  final Discount[] discounts;

    public DefaultSubscriptionBillingEvent(final SubscriptionBaseTransitionType type, final Plan plan,
                                           final PlanPhase planPhase, final DateTime effectiveDate,
                                           final Long totalOrdering, final Integer bcdLocal, final Integer quantity,
                                           final DateTime catalogEffectiveDate, DiscountStack[] discountStacks , Discount[] discounts) {
        this.type = type;
        this.plan = plan;
        this.planPhase = planPhase;
        this.effectiveDate = effectiveDate;
        this.totalOrdering = totalOrdering;
        this.bcdLocal = bcdLocal;
        this.quantity = quantity;
        this.catalogEffectiveDate = catalogEffectiveDate;
        this.discountStacks = discountStacks;
        this.discounts = discounts;
    }

    @Override
    public SubscriptionBaseTransitionType getType() {
        return type;
    }

    @Override
    public Plan getPlan() {
        return plan;
    }

    @Override
    public PlanPhase getPlanPhase() {
        return planPhase;
    }

    @Override
    public DiscountStack[] getDiscounts() {
        return new DiscountStack[0];
    }

    @Override
    public Discount[] getDiscount() {
        return new Discount[0];
    }

    @Override
    public DateTime getEffectiveDate() {
        return effectiveDate;
    }

    @Override
    public Long getTotalOrdering() {
        return totalOrdering;
    }

    @Override
    public Integer getBcdLocal() {
        return bcdLocal;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public DateTime getCatalogEffectiveDate() {
        return catalogEffectiveDate;
    }

    @Override
    public String toString() {
        return "DefaultSubscriptionBillingEvent{" +
               "type=" + type +
               ", plan='" + plan.getName() + '\'' +
               ", planPhase='" + planPhase.getName() + '\'' +
               ", effectiveDate=" + effectiveDate +
               ", totalOrdering=" + totalOrdering +
               ", catalogEffectiveDate=" + catalogEffectiveDate +
               ", bcdLocal=" + bcdLocal +
               ", quantity=" + quantity +
               '}';
    }

    @Override
    public int compareTo(final SubscriptionBillingEvent o) {
        if (getEffectiveDate().compareTo(o.getEffectiveDate()) != 0) {
            return getEffectiveDate().compareTo(o.getEffectiveDate());
        } else if (getTotalOrdering().compareTo(o.getTotalOrdering()) != 0) {
            return getTotalOrdering().compareTo(o.getTotalOrdering());
        } else {
            final Date effectiveDate = getPlan().getCatalog().getEffectiveDate();
            final Date oEeffectiveDate = o.getPlan().getCatalog().getEffectiveDate();
            return effectiveDate.compareTo(oEeffectiveDate);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SubscriptionBillingEvent that = (DefaultSubscriptionBillingEvent) o;
        return getType() == that.getType() &&
               Objects.equals(getPlan(), that.getPlan()) &&
               Objects.equals(getPlanPhase(), that.getPlanPhase()) &&
               Objects.equals(getEffectiveDate(), that.getEffectiveDate()) &&
               Objects.equals(getTotalOrdering(), that.getTotalOrdering()) &&
               Objects.equals(getBcdLocal(), that.getBcdLocal()) &&
               Objects.equals(getQuantity(), that.getQuantity()) &&
               Objects.equals(getCatalogEffectiveDate(), that.getCatalogEffectiveDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getPlan(), getPlanPhase(), getEffectiveDate(),
                            getTotalOrdering(), getBcdLocal(), getQuantity(), getCatalogEffectiveDate());
    }
}
