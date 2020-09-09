import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.shoppingcard.model.Campaign;
import com.shoppingcard.model.Category;
import com.shoppingcard.model.DiscountType;

public class ModelTest {

	@Test
	public void campaign_whenCategoryIsNull() {
	
		Category category = null;
		Campaign campaign = new Campaign(category, 10, 1, DiscountType.PRICE);
		assertThat(campaign.getCategory() != null).isFalse();
	}
	
	@Test
	public void campaign_whenDiscountTypeIsNull() {
	
		Category category = new Category("new category");
		Campaign campaign = new Campaign(category, 10, 1, null);
		assertThat(campaign.getDiscountType() != null).isFalse();
	}
	
	
	@Test
	public void campaign_whenDiscountAmountIsLessThanZero() {
	
		Category category = new Category("discount Category");
        Campaign campaign = new Campaign(category, -1, 1, DiscountType.PRICE);

		assertThat(campaign.getDiscount() >= 0).isFalse();
	}
	
    @Test
    public void addCategoryList_whenChildCategoryIsNotNull() {

        Category category = new Category("category");
        Category ChildCategory = new Category("ChildCategory");

        category.add(ChildCategory);

        assertThat(category.getChildCategoryList()).hasSize(1);
        assertThat(category.getChildCategoryList()).containsOnly(ChildCategory);
    }

    @Test
    public void addCategoryListSame_whenChildCategoryExistsInCategoryList() {

        Category category = new Category("category");
        Category ChildCategory = new Category("ChildCategory");

        category.add(ChildCategory);
        category.add(ChildCategory);

        assertThat(category.getChildCategoryList()).hasSize(1);
        assertThat(category.getChildCategoryList()).containsOnly(ChildCategory);
    }

    @Test
    public void add_shouldNotAddChildCategoryToCategoryList_whenChildCategoryIsNull() {

        Category category = new Category("category");
        Category ChildCategory = null;

        category.add(ChildCategory);

        assertThat(category.getChildCategoryList()).isEmpty();
    }

    @Test
    public void add_shouldNotAddChildCategoryToCategoryList_whenChildCategoryIsEqualToCategoryItself() {

        Category category = new Category("category");
        Category ChildCategory = category;

        category.add(ChildCategory);

        assertThat(category.getChildCategoryList()).isEmpty();
    }
}
