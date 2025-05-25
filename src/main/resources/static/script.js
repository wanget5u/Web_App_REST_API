const itemForm = document.getElementById('itemForm');
const filterForm = document.getElementById('filterForm');
const itemGrid = document.getElementById('itemGrid');
const cancelEditButton = document.getElementById('cancelEditButton');
const submitButton = document.getElementById('submitButton');

itemForm.onsubmit = async (element) =>
    {
        element.preventDefault();
        const formData = new FormData(itemForm);
        const id = formData.get("id");

        const imageFile = document.getElementById('imageInput').files[0];

        if (imageFile)
        {formData.append("imageFile", imageFile)}

        const method = id ? 'PUT' : 'POST';
        const url = id ? `/data/${id}` : '/data';

        await fetch(url,
            {
                method,
                body: formData
            });

        console.log(url, method, formData)

        itemForm.reset();
        itemForm.id.value = '';
        cancelEditButton.style.display = 'none';
        submitButton.textContent = 'Dodaj przedmiot';
        await loadItems(filterForm.sortBy.value);
    };

async function loadItems(sortBy)
{
    const response = await fetch(`/data?sortBy=${sortBy}`);
    const items = await response.json();

    itemGrid.innerHTML = '';

    items.forEach((item) =>
    {
        const card = document.createElement("div");
        card.classList.add("item-card")
        card.innerHTML = `
            <img src="/data/${item.id}/image" alt="" style="max-width:100%; height:auto;" />
            <h3>${item.name}</h3>
            <p>${item.price.toFixed(2)} zł</p>
            <div>
                <button class="cardButton" onclick='editItem(${JSON.stringify(item)})'>Edytuj</button>
                <button class="cardButton" onclick='deleteItem(${item.id})'>Usuń</button>
            </div>`;
        itemGrid.appendChild(card);
    })
}

async function editItem(item)
{
    itemForm.id.value = item.id;
    itemForm.name.value = item.name;
    itemForm.price.value = item.price;

    cancelEditButton.style.display = 'inline-block';
    submitButton.textContent = 'Zaktualizuj przedmiot';
}

async function deleteItem(id)
{
    await fetch(`/data/${id}`, {method: 'DELETE'});
    await loadItems(filterForm.sortBy.value);
}

window.addEventListener('DOMContentLoaded', () =>
{loadItems(filterForm.sortBy.value || 'name');});

cancelEditButton.onclick = () =>
{
    itemForm.reset();
    itemForm.id.value = '';
    cancelEditButton.style.display = 'none';
    submitButton.textContent = 'Dodaj przedmiot';
};